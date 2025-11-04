package com.mindtech.newsletter.service;

import com.mindtech.newsletter.dto.InscricaoDto;
import com.mindtech.newsletter.exception.MindTechErrorException;
import com.mindtech.newsletter.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.mindtech.newsletter.utils.EmailUtils.isValidEmail;
import static com.mindtech.newsletter.utils.LogUtil.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@Transactional
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Transactional
    public InscricaoDto inscricao(InscricaoDto inscricaoDto) {
        logInicioInscricao(inscricaoDto.getEmail());

        validarEmail(inscricaoDto.getEmail());
        verificarSeEmailExiste(inscricaoDto.getEmail());
        inscricaoDto.setDataHoraCriacao(LocalDateTime.now());

        return inscricaoRepository.inscricao(inscricaoDto);
    }

    @Transactional
    public void cancelarInscricao(String email) {
        logInicioCancelamentoDeInscricao(email);

        validarEmail(email);
        verificarSeEmailNaoExiste(email);

        inscricaoRepository.cancelarInscricao(email);
    }


    private void validarEmail(String email) {
        logValidacaoEmail(email);

        if (isBlank(email)) {
            throw new MindTechErrorException("O email é obrigatório.");
        }
        if (!isValidEmail(email)) {
            throw new MindTechErrorException("O email inserido é inválido.");
        }
        if (email.length() > 80) {
            throw new MindTechErrorException("O tamanho do email inserido deve ser menor ou igual à 80 caracteres.");
        }
    }

    private void verificarSeEmailExiste(String email) {
        logValidacaoSeEmailExiste(email);

        if (inscricaoRepository.existeEmail(email)) {
            throw new MindTechErrorException("O email inserido já está cadastrado no Newsletter Mindtech.");
        }
    }

    private void verificarSeEmailNaoExiste(String email) {
        logValidacaoSeEmailNaoExiste(email);

        if (!inscricaoRepository.existeEmail(email)) {
            throw new MindTechErrorException("O email inserido não está cadastrado no Newsletter Mindtech.");
        }
    }


}
