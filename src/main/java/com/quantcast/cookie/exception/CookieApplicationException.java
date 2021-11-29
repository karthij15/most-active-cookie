package com.quantcast.cookie.exception;

public class CookieApplicationException extends RuntimeException {

    public static final String ERR_MESSAGE = "Exception during processing";

    public CookieApplicationException() {
        super(ERR_MESSAGE);
    }

    public CookieApplicationException(String message) {
        super(message);
    }

    public CookieApplicationException(Throwable cause) {
        super(ERR_MESSAGE, cause);
    }

    public CookieApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
