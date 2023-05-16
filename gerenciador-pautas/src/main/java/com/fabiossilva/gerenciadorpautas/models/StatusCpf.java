package com.fabiossilva.gerenciadorpautas.models;

public enum StatusCpf {
    ABLE_TO_VOTE {
        @Override
        public Boolean verificaStatus() {
            return true;
        }
    },
    UNABLE_TO_VOTE {
        @Override
        public Boolean verificaStatus() {
            return false;
        }
    };

    public abstract Boolean verificaStatus();
}
