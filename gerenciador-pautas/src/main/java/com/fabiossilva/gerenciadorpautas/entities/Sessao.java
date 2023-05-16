package com.fabiossilva.gerenciadorpautas.entities;

import com.fabiossilva.gerenciadorpautas.entities.votos.VotosJSONB;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLJsonbJdbcType;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "closed")
    private Boolean closed = false;

    @NotNull
    @Column(name = "ends_in")
    private LocalDateTime endsIn;

    @Lob
    @JdbcType(PostgreSQLJsonbJdbcType.class)
    @Column(name = "votos", columnDefinition = "jsonb")
    private VotosJSONB votos;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public LocalDateTime getEndsIn() {
        return endsIn;
    }

    public void setEndsIn(LocalDateTime endsIn) {
        this.endsIn = endsIn;
    }

    public VotosJSONB getVotos() {
        return votos;
    }

    public void setVotos(VotosJSONB votos) {
        this.votos = votos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sessao sessao = (Sessao) o;

        if (!id.equals(sessao.id)) return false;
        if (!pauta.equals(sessao.pauta)) return false;
        return createdAt.equals(sessao.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + pauta.hashCode();
        result = 31 * result + createdAt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", pauta=" + pauta +
                ", createdAt=" + createdAt +
                ", closed=" + closed +
                ", endsIn=" + endsIn +
                ", votos=" + votos +
                '}';
    }
}
