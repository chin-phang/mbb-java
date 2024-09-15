package com.mbb.assessment.exception;

import java.io.Serial;

public class AuthIllegalArgumentException extends Exception {

    @Serial
    private static final long serialVersionUID = 8422192795576913263L;

    public AuthIllegalArgumentException(String message) {
        super(message);
    }

    public AuthIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
