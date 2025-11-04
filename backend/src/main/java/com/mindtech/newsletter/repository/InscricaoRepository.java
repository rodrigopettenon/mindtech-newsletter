package com.mindtech.newsletter.repository;

import com.mindtech.newsletter.dto.InscricaoDto;
import com.mindtech.newsletter.exception.MindTechErrorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mindtech.newsletter.utils.LogUtil.*;

@Repository
public class InscricaoRepository {

    @PersistenceContext
    private EntityManager em;

    public InscricaoDto inscricao(InscricaoDto inscricaoDto) {
        try {
            String sql = " INSERT INTO inscricao (email, data_hora_criacao) VALUES (:email, :dataHoraCriacao) ";

            Query query = em.createNativeQuery(sql)
                    .setParameter("email", inscricaoDto.getEmail())
                    .setParameter("dataHoraCriacao", inscricaoDto.getDataHoraCriacao());

            query.executeUpdate();
            logSucessoInscricao(inscricaoDto.getEmail());

            return inscricaoDto;

        } catch (MindTechErrorException excecao) {
            logErroInesperadoInscricao(inscricaoDto.getEmail(), excecao);
            throw new MindTechErrorException("Erro inesperado ao salvar email inserido.");
        }
    }

    public void cancelarInscricao(String email) {
        try {
            String sql = " DELETE FROM inscricao WHERE email = :email ";

            Query query = em.createNativeQuery(sql)
                    .setParameter("email", email);

            query.executeUpdate();
            logSucessoCancelarInscricao(email);

        } catch (MindTechErrorException excecao) {
            logErroInesperadoCancelarInscricao(email, excecao);
            throw new MindTechErrorException("Erro inesperado ao cancelar inscrição do Newsletter Mindtech.");
        }
    }

    public Boolean existeEmail(String email) {
        try {
            String sql = " SELECT 1 FROM inscricao WHERE email = :email LIMIT 1 ";

            Query query = em.createNativeQuery(sql)
                    .setParameter("email", email);

            List<?> listaDeResultado = query.getResultList();
            logSucessoVerificarExistencia(email);
            return !listaDeResultado.isEmpty();

        } catch (MindTechErrorException excecao) {
            logErroInesperadoAoVerificarExistencia(email, excecao);
            throw new MindTechErrorException("Erro inesperado ao verificar existência de inscrição do email inserido.");
        }
    }
}
