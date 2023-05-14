package com.fabiossilva.gerenciadorpautas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "closed")
    private Boolean closed;

    @Column(name = "ends_in")
    private LocalDateTime ends_in;

    public Sessao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public LocalDateTime getEnds_in() {
        return ends_in;
    }

    public void setEnds_in(LocalDateTime ends_in) {
        this.ends_in = ends_in;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sessao sessao = (Sessao) o;

        if (!id.equals(sessao.id)) return false;
        if (!pauta.equals(sessao.pauta)) return false;
        return created_at.equals(sessao.created_at);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + pauta.hashCode();
        result = 31 * result + created_at.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", pauta=" + pauta +
                ", created_at=" + created_at +
                ", closed=" + closed +
                ", ends_in=" + ends_in +
                '}';
    }
}
