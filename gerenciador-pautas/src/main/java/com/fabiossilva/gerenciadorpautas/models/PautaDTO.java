package com.fabiossilva.gerenciadorpautas.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class PautaDTO {
    private Long id;

    @NotNull(message = "Não pode ser vazio ou nulo")
    private Long associadoId;

    @NotNull(message = "Não pode ser vazio ou nulo")
    @NotBlank(message = "Não pode ser vazio ou nulo")
    @Size(min = 3, max = 50, message = "Não pode menos que 3 caracteres e mais de 50 caracteres")
    private String nome;

    @Size(max = 254)
    private String descricao;

    public PautaDTO() {
    }

    public PautaDTO(Long id, Long associadoId, String nome, String descricao) {
        this.id = id;
        this.associadoId = associadoId;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

        PautaDTO pautaDTO = (PautaDTO) o;

        if (!Objects.equals(id, pautaDTO.id)) return false;
        if (!Objects.equals(associadoId, pautaDTO.associadoId))
            return false;
        if (!Objects.equals(nome, pautaDTO.nome)) return false;
        return Objects.equals(descricao, pautaDTO.descricao);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (associadoId != null ? associadoId.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PautaDTO{" +
                "id=" + id +
                ", associadoId=" + associadoId +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
