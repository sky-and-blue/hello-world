package com.itany.netClass.exception;

/**
 * Date: 2019年06月25日 上午10:50
 */
public class TypedNameExistException extends Exception{
    public TypedNameExistException() {
        super();
    }

    public TypedNameExistException(String message) {
        super(message);
    }

    public TypedNameExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypedNameExistException(Throwable cause) {
        super(cause);
    }

    protected TypedNameExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
