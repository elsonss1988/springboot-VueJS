package com.elson.mycash.exception;

public class UsuarioException extends RuntimeException{

    private static final long serialVersionUID =111 ;
    public UsuarioException(String s) {
    }

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioException(Throwable cause) {
        super(cause);
    }

    public UsuarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UsuarioException() {
    }
}
