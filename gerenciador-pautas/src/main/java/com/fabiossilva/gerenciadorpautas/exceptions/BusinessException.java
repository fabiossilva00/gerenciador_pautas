package com.fabiossilva.gerenciadorpautas.exceptions;

import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;

public class BusinessException extends GenericException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, ErrorResponse errorResponse) {
        super(message, errorResponse);
    }
}
