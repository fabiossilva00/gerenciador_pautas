package com.fabiossilva.gerenciadorpautas.models;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.fabiossilva.gerenciadorpautas.constants.ApplicationConsts.MESSAGEM_ERRO_NULOVAZIO;

public class SessaoDTO {
    private Long id;

    @NotNull(message = MESSAGEM_ERRO_NULOVAZIO)
    private Long idPauta;

    private LocalDateTime criadoEm;

    private Boolean closed = false;

    private int tempoEmAberto = 1;

    private int votosSIM = 0;
    private int votosNAO = 0;

    public SessaoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public LocalDateTime getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public int getTempoEmAberto() {
        return tempoEmAberto;
    }

    public void setTempoEmAberto(int tempoEmAberto) {
        this.tempoEmAberto = tempoEmAberto;
    }

    public int getVotosSIM() {
        return votosSIM;
    }

    public void setVotosSIM(int votosSIM) {
        this.votosSIM = votosSIM;
    }

    public int getVotosNAO() {
        return votosNAO;
    }

    public void setVotosNAO(int votosNAO) {
        this.votosNAO = votosNAO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessaoDTO sessaoDTO = (SessaoDTO) o;

        if (tempoEmAberto != sessaoDTO.tempoEmAberto) return false;
        if (!Objects.equals(id, sessaoDTO.id)) return false;
        if (!Objects.equals(idPauta, sessaoDTO.idPauta)) return false;
        if (!Objects.equals(criadoEm, sessaoDTO.criadoEm)) return false;
        return Objects.equals(closed, sessaoDTO.closed);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idPauta != null ? idPauta.hashCode() : 0);
        result = 31 * result + (criadoEm != null ? criadoEm.hashCode() : 0);
        result = 31 * result + (closed != null ? closed.hashCode() : 0);
        result = 31 * result + tempoEmAberto;
        return result;
    }

    @Override
    public String toString() {
        return "SessaoDTO{" +
                "id=" + id +
                ", idPauta=" + idPauta +
                ", createdAt=" + criadoEm +
                ", closed=" + closed +
                ", tempoEmAberto=" + tempoEmAberto +
                ", votosSIM=" + votosSIM +
                ", votosNAO=" + votosNAO +
                '}';
    }
}
