package com.h2t.springboot.service.exception;

public class UpdataException extends ServiceException{
    public UpdataException() {
        super();
    }

    public UpdataException(String message) {
        super(message);
    }

    public UpdataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdataException(Throwable cause) {
        super(cause);
    }

    protected UpdataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
