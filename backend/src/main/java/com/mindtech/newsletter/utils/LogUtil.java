package com.mindtech.newsletter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);


    private static void logInicioDeProcesso(String processo, Object valor) {
        logger.info("[INÍCIO] Iniciando processo de {}: {}", processo, valor);
    }

    public static void logInicioInscricao(String email) {
        logInicioDeProcesso("inscrição", email);
    }

    public static void logInicioCancelamentoDeInscricao(String email) {
        logInicioDeProcesso("cancelamento de inscrição", email);
    }


    private static void logValidacao(String campo, Object valor) {
        logger.info("[VALIDAÇÃO] Validando {}: {} ", campo, valor);
    }

    public static void logValidacaoEmail(String email) {
        logValidacao("email", email);
    }

    public static void logValidacaoSeEmailExiste(String email) {
        logValidacao("se email existe", email);
    }

    public static void logValidacaoSeEmailNaoExiste(String email) {
        logValidacao("se email não existe", email);
    }

    private static void logSucesso(String processo, Object valor) {
        logger.info("[SUCESSO] Sucesso ao {}: {}", processo, valor);
    }

    public static void logSucessoInscricao(String email) {
        logSucesso("inscrever-se", email);
    }

    public static void logSucessoCancelarInscricao(String email) {
        logSucesso("cancelar inscrição", email);
    }

    public static void logSucessoVerificarExistencia(String email) {
        logSucesso("verificar existencia", email);
    }

    private static void logErroInesperado(String processo, Object valor, Exception excecao) {
        logger.error("[ERRO] Erro inesperado ao {} {}: {}", processo, valor, excecao.getMessage(), excecao);
    }

    public static void logErroInesperadoInscricao(String email, Exception excecao) {
        logErroInesperado("inscrever-se", email, excecao);
    }

    public static void logErroInesperadoCancelarInscricao(String email, Exception excecao) {
        logErroInesperado("cancelar inscrição", email, excecao);
    }

    public static void logErroInesperadoAoVerificarExistencia(String email, Exception excecao) {
        logErroInesperado("verificar existência", email, excecao);
    }
}
