package com.fabiossilva.gerenciadorpautas.entities.votos;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class VotoPK {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @Column(name = "cpf")
    @Size(max = 11, min = 11)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "valor")
    private TipoVoto valor;

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoVoto getValor() {
        return valor;
    }

    public void setValor(TipoVoto valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotoPK votoPK = (VotoPK) o;

        if (!Objects.equals(sessao, votoPK.sessao)) return false;
        if (!Objects.equals(cpf, votoPK.cpf)) return false;
        return valor == votoPK.valor;
    }

    @Override
    public int hashCode() {
        int result = sessao != null ? sessao.hashCode() : 0;
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VotoPK{" +
                "sessao=" + sessao +
                ", cpf='" + cpf + '\'' +
                ", valor=" + valor +
                '}';
    }
}
