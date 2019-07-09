package com.itany.netClass.exception;

public class CourseNameIsExistsException extends Exception {

    public CourseNameIsExistsException() {
        super();
    }

    public CourseNameIsExistsException(String message) {
        super(message);
    }

    public CourseNameIsExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNameIsExistsException(Throwable cause) {
        super(cause);
    }

    protected CourseNameIsExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
