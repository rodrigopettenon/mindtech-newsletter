package com.mindtech.newsletter.controller;

import com.mindtech.newsletter.dto.InscricaoDto;
import com.mindtech.newsletter.dto.StandardObjectReturn;
import com.mindtech.newsletter.exception.MindTechErrorException;
import com.mindtech.newsletter.service.InscricaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class InscricaoControllerTest {

    @Mock
    private InscricaoService inscricaoService;

    @InjectMocks
    private InscricaoController inscricaoController;

    private InscricaoDto inscricaoDto;
    private String emailValido;

    @BeforeEach
    void setUp() {
        emailValido = "usuario@exemplo.com";

        inscricaoDto = new InscricaoDto();
        inscricaoDto.setEmail(emailValido);
        inscricaoDto.setDataHoraCriacao(LocalDateTime.now());
    }

    @Test
    void deveRealizarInscricaoComSucesso() {
        InscricaoDto inscricaoRetorno = new InscricaoDto();
        inscricaoRetorno.setId(1L);
        inscricaoRetorno.setEmail(emailValido);
        inscricaoRetorno.setDataHoraCriacao(LocalDateTime.now());

        when(inscricaoService.inscricao(any(InscricaoDto.class))).thenReturn(inscricaoRetorno);

        ResponseEntity<?> response = inscricaoController.inscricaoNewsletter(inscricaoDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // ← CORRIGIDO: CREATED ao invés de OK

        StandardObjectReturn responseBody = (StandardObjectReturn) response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.CREATED.value(), responseBody.getStatus()); // ← MANTIDO: 201
        assertNotNull(responseBody.getTimestamp());
        assertNull(responseBody.getMessage());

        InscricaoDto inscricaoResponse = (InscricaoDto) responseBody.getObject();
        assertNotNull(inscricaoResponse);
        assertEquals(1L, inscricaoResponse.getId());
        assertEquals(emailValido, inscricaoResponse.getEmail());

        verify(inscricaoService, times(1)).inscricao(inscricaoDto);
    }

    @Test
    void deveCancelarInscricaoComSucesso() {
        doNothing().when(inscricaoService).cancelarInscricao(anyString());

        ResponseEntity<?> response = inscricaoController.cancelarInscricao(emailValido);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // ← MANTIDO: OK

        StandardObjectReturn responseBody = (StandardObjectReturn) response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus()); // ← CORRIGIDO: OK.value() ao invés de CREATED.value()
        assertNotNull(responseBody.getTimestamp());
        assertNull(responseBody.getMessage());
        assertEquals("Inscrição cancelada com sucesso!", responseBody.getObject());

        verify(inscricaoService, times(1)).cancelarInscricao(emailValido);
    }

    @Test
    void deveLancarExcecaoQuandoServiceLancarExcecaoNaInscricao() {
        String mensagemErro = "Email já cadastrado";
        when(inscricaoService.inscricao(any(InscricaoDto.class)))
                .thenThrow(new MindTechErrorException(mensagemErro));

        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoController.inscricaoNewsletter(inscricaoDto)
        );

        assertEquals(mensagemErro, exception.getMessage());
        verify(inscricaoService, times(1)).inscricao(inscricaoDto);
    }

    @Test
    void deveLancarExcecaoQuandoServiceLancarExcecaoNoCancelamento() {
        String mensagemErro = "Email não encontrado";
        doThrow(new MindTechErrorException(mensagemErro))
                .when(inscricaoService).cancelarInscricao(anyString());

        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoController.cancelarInscricao(emailValido)
        );

        assertEquals(mensagemErro, exception.getMessage());
        verify(inscricaoService, times(1)).cancelarInscricao(emailValido);
    }

    @Test
    void deveCriarInscricaoDtoValido() {

        InscricaoDto dto = new InscricaoDto();
        dto.setId(1L);
        dto.setEmail("teste@exemplo.com");
        LocalDateTime dataHora = LocalDateTime.now();
        dto.setDataHoraCriacao(dataHora);

        assertEquals(1L, dto.getId());
        assertEquals("teste@exemplo.com", dto.getEmail());
        assertEquals(dataHora, dto.getDataHoraCriacao());
    }

    @Test
    void deveCriarStandardObjectReturnValido() {
        Instant timestamp = Instant.now();
        Integer status = 200;
        String message = "Sucesso";
        Object object = new Object();

        StandardObjectReturn standardObjectReturn = new StandardObjectReturn(
                timestamp, status, message, object
        );

        assertEquals(timestamp, standardObjectReturn.getTimestamp());
        assertEquals(status, standardObjectReturn.getStatus());
        assertEquals(message, standardObjectReturn.getMessage());
        assertEquals(object, standardObjectReturn.getObject());
    }

    @Test
    void deveCriarStandardObjectReturnComConstrutorVazio() {
        StandardObjectReturn standardObjectReturn = new StandardObjectReturn();

        assertNotNull(standardObjectReturn);
        assertNull(standardObjectReturn.getTimestamp());
        assertNull(standardObjectReturn.getStatus());
        assertNull(standardObjectReturn.getMessage());
        assertNull(standardObjectReturn.getObject());
    }
}