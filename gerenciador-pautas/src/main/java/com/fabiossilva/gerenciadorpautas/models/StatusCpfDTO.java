package com.fabiossilva.gerenciadorpautas.models;

public class StatusCpfDTO {

    private StatusCpf status;

    public StatusCpfDTO() {
    }

    public StatusCpfDTO(StatusCpf status) {
        this.status = status;
    }

    public StatusCpf getStatus() {
        return status;
    }

    public void setStatus(StatusCpf status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusCpfDTO that = (StatusCpfDTO) o;

        return status == that.status;
    }

    @Override
    public int hashCode() {
        return status != null ? status.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StatusCpfDTO{" +
                "status=" + status +
                '}';
    }
}
