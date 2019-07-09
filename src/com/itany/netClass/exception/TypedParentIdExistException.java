package com.itany.netClass.exception;

/**
 * Date: 2019年06月25日 上午10:58
 */
public class TypedParentIdExistException extends Exception {
    public TypedParentIdExistException() {
    }

    public TypedParentIdExistException(String message) {
        super(message);
    }

    public TypedParentIdExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypedParentIdExistException(Throwable cause) {
        super(cause);
    }

    public TypedParentIdExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
