package com.fabiossilva.gerenciadorpautas.exceptions;

import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;

public class SessaoException extends GenericException {
    public SessaoException() {
    }

    public SessaoException(String message) {
        super(message);
    }

    public SessaoException(String message, ErrorResponse errorResponse) {
        super(message, errorResponse);
    }
}
