package com.mbb.assessment.exception;

import java.io.Serial;

public class TransactionNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = -3292925678046267679L;

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotFoundException(Throwable cause) {
        super(cause);
    }
}
