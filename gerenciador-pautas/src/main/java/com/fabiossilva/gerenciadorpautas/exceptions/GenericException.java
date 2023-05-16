package com.fabiossilva.gerenciadorpautas.exceptions;

import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;

public class GenericException extends RuntimeException {

    private ErrorResponse errorResponse;

    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, ErrorResponse errorResponse) {
        super(message);
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
