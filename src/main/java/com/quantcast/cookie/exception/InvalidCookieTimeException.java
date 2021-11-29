package com.quantcast.cookie.exception;

public class InvalidCookieTimeException extends RuntimeException {

    public static final String ERR_MESSAGE = "Invalid cookie timestamp";

    public InvalidCookieTimeException() {
        super(ERR_MESSAGE);
    }

    public InvalidCookieTimeException(String message) {
        super(message);
    }

    public InvalidCookieTimeException(Throwable cause) {
        super(ERR_MESSAGE, cause);
    }

    public InvalidCookieTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
