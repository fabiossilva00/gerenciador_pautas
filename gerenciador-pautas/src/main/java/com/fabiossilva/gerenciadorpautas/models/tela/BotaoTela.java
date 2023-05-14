package com.fabiossilva.gerenciadorpautas.models.tela;

import java.util.Objects;

public class BotaoTela {

    private String texto;
    private String url;
    private Object body;

    public BotaoTela() {
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

        BotaoTela botaoTela = (BotaoTela) o;

        if (!Objects.equals(texto, botaoTela.texto)) return false;
        if (!Objects.equals(url, botaoTela.url)) return false;
        return Objects.equals(body, botaoTela.body);
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
        return "BotaoTela{" +
                "texto='" + texto + '\'' +
                ", url='" + url + '\'' +
                ", body=" + body +
                '}';
    }
}
