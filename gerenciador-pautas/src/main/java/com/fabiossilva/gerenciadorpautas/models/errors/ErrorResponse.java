package com.fabiossilva.gerenciadorpautas.models.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class ErrorResponse {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> details;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, Map<String, String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
