package com.quantcast.cookie.exception;

public class InvalidCookieValueException extends RuntimeException {

    public static final String ERR_MESSAGE = "Invalid cookie value";

    public InvalidCookieValueException() {
        super(ERR_MESSAGE);
    }

    public InvalidCookieValueException(String message) {
        super(message);
    }

    public InvalidCookieValueException(Throwable cause) {
        super(ERR_MESSAGE, cause);
    }

    public InvalidCookieValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
