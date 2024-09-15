package com.mbb.assessment.exception;

import java.io.Serial;

public class AuthNullPointerException extends Exception {

    @Serial
    private static final long serialVersionUID = 2655205425113722923L;

    public AuthNullPointerException(String message) {
        super(message);
    }

    public AuthNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthNullPointerException(Throwable cause) {
        super(cause);
    }
}
