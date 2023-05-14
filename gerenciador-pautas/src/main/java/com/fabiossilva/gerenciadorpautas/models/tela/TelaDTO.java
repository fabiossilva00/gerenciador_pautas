package com.fabiossilva.gerenciadorpautas.models.tela;

import java.util.List;
import java.util.Objects;

public class TelaDTO<T> {
    private TipoTela tipoTela;
    private String titulo;
    private List<T> itens;

    public TelaDTO(TipoTela tipoTela, String titulo) {
        this.tipoTela = tipoTela;
        this.titulo = titulo;
    }

    public TipoTela getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(TipoTela tipoTela) {
        this.tipoTela = tipoTela;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<T> getItens() {
        return itens;
    }

    public void setItens(List<T> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TelaDTO<?> telaDTO = (TelaDTO<?>) o;

        if (tipoTela != telaDTO.tipoTela) return false;
        if (!Objects.equals(titulo, telaDTO.titulo)) return false;
        return Objects.equals(itens, telaDTO.itens);
    }

    @Override
    public int hashCode() {
        int result = tipoTela.hashCode();
        result = 31 * result + titulo.hashCode();
        result = 31 * result + itens.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TelaDTO{" +
                "tipoTela=" + tipoTela +
                ", titulo='" + titulo + '\'' +
                ", itens=" + itens +
                '}';
    }
}
