package com.fabiossilva.gerenciadorpautas.entities;

import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import jakarta.persistence.*;

@Embeddable
public class VotoPK {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @Column(name = "associado_id")
    private Long associadoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "valor")
    private TipoVoto valor;

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
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

        if (!sessao.equals(votoPK.sessao)) return false;
        if (!associadoId.equals(votoPK.associadoId)) return false;
        return valor == votoPK.valor;
    }

    @Override
    public int hashCode() {
        int result = sessao.hashCode();
        result = 31 * result + associadoId.hashCode();
        result = 31 * result + valor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VotoPK{" +
                "session=" + sessao +
                ", associadoId=" + associadoId +
                ", value=" + valor +
                '}';
    }
}
