package com.quantcast.cookie.exception;

public class CLIParseException extends RuntimeException {

    public static final String ERR_MESSAGE = "Invalid command line arguments";

    public CLIParseException() {
        super(ERR_MESSAGE);
    }

    public CLIParseException(String message) {
        super(message);
    }

    public CLIParseException(Throwable cause) {
        super(ERR_MESSAGE, cause);
    }

    public CLIParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
