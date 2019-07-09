package com.itany.netClass.exception;


public class ResourceNameIsExistsException extends Exception {
    public ResourceNameIsExistsException() {
    }

    public ResourceNameIsExistsException(String message) {
        super(message);
    }

    public ResourceNameIsExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNameIsExistsException(Throwable cause) {
        super(cause);
    }

    public ResourceNameIsExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
