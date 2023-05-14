package com.fabiossilva.gerenciadorpautas.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "associado_id", length = 50, nullable = false)
    private Long associadoId;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 254)
    private String descricao;

    public Pauta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id) && Objects.equals(nome, pauta.nome) && Objects.equals(associadoId, pauta.associadoId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + associadoId.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", associadoId=" + associadoId +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
