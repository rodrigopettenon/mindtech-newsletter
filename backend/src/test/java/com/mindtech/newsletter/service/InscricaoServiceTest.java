package com.mindtech.newsletter.service;

import com.mindtech.newsletter.dto.InscricaoDto;
import com.mindtech.newsletter.exception.MindTechErrorException;
import com.mindtech.newsletter.repository.InscricaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InscricaoServiceTest {

    @Mock
    private InscricaoRepository inscricaoRepository;

    @InjectMocks
    private InscricaoService inscricaoService;

    private InscricaoDto inscricaoDto;
    private String emailValido;

    @BeforeEach
    void setUp() {
        emailValido = "usuario@exemplo.com";

        inscricaoDto = new InscricaoDto();
        inscricaoDto.setEmail(emailValido);
    }

    @Test
    void deveRealizarInscricaoComSucesso() {
        // Arrange
        InscricaoDto inscricaoRetorno = new InscricaoDto();
        inscricaoRetorno.setId(1L);
        inscricaoRetorno.setEmail(emailValido);
        inscricaoRetorno.setDataHoraCriacao(LocalDateTime.now());

        when(inscricaoRepository.existeEmail(emailValido)).thenReturn(false);
        when(inscricaoRepository.inscricao(any(InscricaoDto.class))).thenReturn(inscricaoRetorno);

        // Act
        InscricaoDto result = inscricaoService.inscricao(inscricaoDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(emailValido, result.getEmail());
        assertNotNull(result.getDataHoraCriacao());

        verify(inscricaoRepository, times(1)).existeEmail(emailValido);
        verify(inscricaoRepository, times(1)).inscricao(inscricaoDto);
    }

    @Test
    void deveCancelarInscricaoComSucesso() {
        // Arrange
        when(inscricaoRepository.existeEmail(emailValido)).thenReturn(true);
        doNothing().when(inscricaoRepository).cancelarInscricao(emailValido);

        // Act
        inscricaoService.cancelarInscricao(emailValido);

        // Assert
        verify(inscricaoRepository, times(1)).existeEmail(emailValido);
        verify(inscricaoRepository, times(1)).cancelarInscricao(emailValido);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void deveLancarExcecaoQuandoEmailVazioOuNuloNaInscricao(String emailInvalido) {
        // Arrange
        inscricaoDto.setEmail(emailInvalido);

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.inscricao(inscricaoDto)
        );

        assertEquals("O email é obrigatório.", exception.getMessage());
        verify(inscricaoRepository, never()).existeEmail(anyString());
        verify(inscricaoRepository, never()).inscricao(any(InscricaoDto.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "email-invalido",
            "email@",
            "@dominio.com",
            "email@dominio",
            "email@.com",
            "email@dominio.",
            "email @dominio.com",
            "email@ dominio.com"
    })
    void deveLancarExcecaoQuandoEmailInvalidoNaInscricao(String emailInvalido) {
        // Arrange
        inscricaoDto.setEmail(emailInvalido);

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.inscricao(inscricaoDto)
        );

        assertEquals("O email inserido é inválido.", exception.getMessage());
        verify(inscricaoRepository, never()).existeEmail(anyString());
        verify(inscricaoRepository, never()).inscricao(any(InscricaoDto.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailMuitoLongoNaInscricao() {
        // Arrange
        String emailLongo = "a".repeat(70) + "@exemplo.com"; // Mais de 80 caracteres
        inscricaoDto.setEmail(emailLongo);

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.inscricao(inscricaoDto)
        );

        assertEquals("O tamanho do email inserido deve ser menor ou igual à 80 caracteres.", exception.getMessage());
        verify(inscricaoRepository, never()).existeEmail(anyString());
        verify(inscricaoRepository, never()).inscricao(any(InscricaoDto.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExisteNaInscricao() {
        // Arrange
        when(inscricaoRepository.existeEmail(emailValido)).thenReturn(true);

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.inscricao(inscricaoDto)
        );

        assertEquals("O email inserido já está cadastrado no Newsletter Mindtech.", exception.getMessage());
        verify(inscricaoRepository, times(1)).existeEmail(emailValido);
        verify(inscricaoRepository, never()).inscricao(any(InscricaoDto.class));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void deveLancarExcecaoQuandoEmailVazioOuNuloNoCancelamento(String emailInvalido) {
        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.cancelarInscricao(emailInvalido)
        );

        assertEquals("O email é obrigatório.", exception.getMessage());
        verify(inscricaoRepository, never()).existeEmail(anyString());
        verify(inscricaoRepository, never()).cancelarInscricao(anyString());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "email-invalido",
            "email@",
            "@dominio.com"
    })
    void deveLancarExcecaoQuandoEmailInvalidoNoCancelamento(String emailInvalido) {
        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.cancelarInscricao(emailInvalido)
        );

        assertEquals("O email inserido é inválido.", exception.getMessage());
        verify(inscricaoRepository, never()).existeEmail(anyString());
        verify(inscricaoRepository, never()).cancelarInscricao(anyString());
    }

    @Test
    void deveLancarExcecaoQuandoEmailNaoExisteNoCancelamento() {
        // Arrange
        when(inscricaoRepository.existeEmail(emailValido)).thenReturn(false);

        // Act & Assert
        MindTechErrorException exception = assertThrows(
                MindTechErrorException.class,
                () -> inscricaoService.cancelarInscricao(emailValido)
        );

        assertEquals("O email inserido não está cadastrado no Newsletter Mindtech.", exception.getMessage());
        verify(inscricaoRepository, times(1)).existeEmail(emailValido);
        verify(inscricaoRepository, never()).cancelarInscricao(anyString());
    }

    @Test
    void deveAceitarEmailComExtensaoLonga() {
        // Arrange
        String emailComExtensaoLonga = "usuario@dominio.muitolong";
        inscricaoDto.setEmail(emailComExtensaoLonga);

        InscricaoDto inscricaoRetorno = new InscricaoDto();
        inscricaoRetorno.setId(1L);
        inscricaoRetorno.setEmail(emailComExtensaoLonga);

        when(inscricaoRepository.existeEmail(emailComExtensaoLonga)).thenReturn(false);
        when(inscricaoRepository.inscricao(any(InscricaoDto.class))).thenReturn(inscricaoRetorno);

        // Act
        InscricaoDto result = inscricaoService.inscricao(inscricaoDto);

        // Assert
        assertNotNull(result);
        verify(inscricaoRepository, times(1)).inscricao(inscricaoDto);
    }

    @Test
    void deveAceitarEmailComSubdominio() {
        // Arrange
        String emailComSubdominio = "usuario@sub.dominio.com";
        inscricaoDto.setEmail(emailComSubdominio);

        InscricaoDto inscricaoRetorno = new InscricaoDto();
        inscricaoRetorno.setId(1L);
        inscricaoRetorno.setEmail(emailComSubdominio);

        when(inscricaoRepository.existeEmail(emailComSubdominio)).thenReturn(false);
        when(inscricaoRepository.inscricao(any(InscricaoDto.class))).thenReturn(inscricaoRetorno);

        // Act
        InscricaoDto result = inscricaoService.inscricao(inscricaoDto);

        // Assert
        assertNotNull(result);
        verify(inscricaoRepository, times(1)).inscricao(inscricaoDto);
    }

    @Test
    void deveDefinirDataHoraCriacaoNaInscricao() {
        // Arrange
        InscricaoDto inscricaoRetorno = new InscricaoDto();
        inscricaoRetorno.setId(1L);
        inscricaoRetorno.setEmail(emailValido);

        when(inscricaoRepository.existeEmail(emailValido)).thenReturn(false);
        when(inscricaoRepository.inscricao(any(InscricaoDto.class))).thenReturn(inscricaoRetorno);

        // Act
        InscricaoDto result = inscricaoService.inscricao(inscricaoDto);

        // Assert
        assertNotNull(inscricaoDto.getDataHoraCriacao());
        assertTrue(inscricaoDto.getDataHoraCriacao().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(inscricaoDto.getDataHoraCriacao().isAfter(LocalDateTime.now().minusSeconds(1)));
    }

}