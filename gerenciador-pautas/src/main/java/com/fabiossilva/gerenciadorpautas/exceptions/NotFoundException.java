package com.fabiossilva.gerenciadorpautas.exceptions;

import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;

public class NotFoundException extends GenericException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, ErrorResponse errorResponse) {
        super(message, errorResponse);
    }
}
