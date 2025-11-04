package com.mindtech.newsletter.repository;

import com.mindtech.newsletter.dto.InscricaoDto;
import com.mindtech.newsletter.exception.MindTechErrorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InscricaoRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    private InscricaoRepository inscricaoRepository;

    private InscricaoDto inscricaoDto;
    private String emailValido;

    @BeforeEach
    void setUp() {
        // Inicializando o repository manualmente
        inscricaoRepository = new InscricaoRepository();

        // Usando reflection para injetar o EntityManager mock
        try {
            var field = InscricaoRepository.class.getDeclaredField("em");
            field.setAccessible(true);
            field.set(inscricaoRepository, entityManager);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao injetar EntityManager mock", e);
        }

        emailValido = "usuario@exemplo.com";

        inscricaoDto = new InscricaoDto();
        inscricaoDto.setEmail(emailValido);
        inscricaoDto.setDataHoraCriacao(LocalDateTime.now());
    }

    @Test
    void deveInserirInscricaoComSucesso() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(eq("email"), anyString())).thenReturn(query);
        when(query.setParameter(eq("dataHoraCriacao"), any())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        // Act
        InscricaoDto result = inscricaoRepository.inscricao(inscricaoDto);

        // Assert
        assertNotNull(result);
        assertEquals(inscricaoDto, result);

        verify(entityManager).createNativeQuery(" INSERT INTO inscricao (email, data_hora_criacao) VALUES (:email, :dataHoraCriacao) ");
        verify(query).setParameter("email", emailValido);
        verify(query).setParameter("dataHoraCriacao", inscricaoDto.getDataHoraCriacao());
        verify(query).executeUpdate();
    }

    @Test
    void deveCancelarInscricaoComSucesso() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(eq("email"), anyString())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        // Act
        inscricaoRepository.cancelarInscricao(emailValido);

        // Assert
        verify(entityManager).createNativeQuery(" DELETE FROM inscricao WHERE email = :email ");
        verify(query).setParameter("email", emailValido);
        verify(query).executeUpdate();
    }

    @Test
    void deveRetornarTrueQuandoEmailExiste() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(eq("email"), anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(1));

        // Act
        Boolean existe = inscricaoRepository.existeEmail(emailValido);

        // Assert
        assertTrue(existe);
        verify(entityManager).createNativeQuery(" SELECT 1 FROM inscricao WHERE email = :email LIMIT 1 ");
        verify(query).setParameter("email", emailValido);
        verify(query).getResultList();
    }

    @Test
    void deveRetornarFalseQuandoEmailNaoExiste() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(eq("email"), anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        // Act
        Boolean existe = inscricaoRepository.existeEmail(emailValido);

        // Assert
        assertFalse(existe);
        verify(query).getResultList();
    }

    @Test
    void deveLancarExcecaoQuandoInscricaoFalha() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);

        // Lançar a exceção específica que seu código espera
        when(query.executeUpdate()).thenThrow(new MindTechErrorException("Erro de banco"));

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoRepository.inscricao(inscricaoDto)
        );

        assertEquals("Erro inesperado ao salvar email inserido.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCancelamentoFalha() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);

        // Lançar a exceção específica que seu código espera
        when(query.executeUpdate()).thenThrow(new MindTechErrorException("Erro de banco"));

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoRepository.cancelarInscricao(emailValido)
        );

        assertEquals("Erro inesperado ao cancelar inscrição do Newsletter Mindtech.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoVerificacaoExistenciaFalha() {
        // Arrange
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);

        // Lançar a exceção específica que seu código espera
        when(query.getResultList()).thenThrow(new MindTechErrorException("Erro de banco"));

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoRepository.existeEmail(emailValido)
        );

        assertEquals("Erro inesperado ao verificar existência de inscrição do email inserido.", exception.getMessage());
    }
}