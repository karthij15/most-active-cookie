package com.quantcast.cookie.exception;

public class InvalidLogException extends RuntimeException {

    public static final String ERR_MESSAGE = "Invalid log file";

    public InvalidLogException() {
        super(ERR_MESSAGE);
    }

    public InvalidLogException(String message) {
        super(message);
    }

    public InvalidLogException(Throwable cause) {
        super(ERR_MESSAGE, cause);
    }

    public InvalidLogException(String message, Throwable cause) {
        super(message, cause);
    }
}
