package com.itany.netClass.exception;

public class CheckDataFormatException extends RuntimeException {
    public CheckDataFormatException() {
        super();
    }

    public CheckDataFormatException(String message) {
        super(message);
    }

    public CheckDataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckDataFormatException(Throwable cause) {
        super(cause);
    }

    protected CheckDataFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
