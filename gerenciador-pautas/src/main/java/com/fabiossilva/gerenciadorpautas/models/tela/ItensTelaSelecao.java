package com.fabiossilva.gerenciadorpautas.models.tela;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

public class ItensTelaSelecao {
    private String texto;
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object body;

    public ItensTelaSelecao(String texto, String url) {
        this.texto = texto;
        this.url = url;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItensTelaSelecao that = (ItensTelaSelecao) o;

        if (!Objects.equals(texto, that.texto)) return false;
        if (!Objects.equals(url, that.url)) return false;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        int result = texto != null ? texto.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItensTelaSelecao{" +
                "texto='" + texto + '\'' +
                ", url='" + url + '\'' +
                ", body=" + body +
                '}';
    }
}
