package com.fabiossilva.gerenciadorpautas.models.tela;

import java.util.Objects;

public class ItensTelaFormulario {
    private String tipo;
    private String texto;
    private String titulo;
    private String valor;

    public ItensTelaFormulario() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItensTelaFormulario that = (ItensTelaFormulario) o;

        if (!Objects.equals(tipo, that.tipo)) return false;
        if (!Objects.equals(texto, that.texto)) return false;
        if (!Objects.equals(titulo, that.titulo)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        int result = tipo != null ? tipo.hashCode() : 0;
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItensTelaFormulario{" +
                "tipo='" + tipo + '\'' +
                ", texto='" + texto + '\'' +
                ", titulo='" + titulo + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
