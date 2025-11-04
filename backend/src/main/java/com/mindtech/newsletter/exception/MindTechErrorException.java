package com.mindtech.newsletter.exception;

public class MindTechErrorException extends RuntimeException {

    private static final long serialVersionUID = 2633780315157720233L;

    private Object object;

    public MindTechErrorException(String mensagem) {
        super(mensagem);
    }

    public MindTechErrorException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public MindTechErrorException(String mensagem, Object object) {
        super(mensagem);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
