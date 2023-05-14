package com.fabiossilva.gerenciadorpautas.models.tela;

import java.util.Objects;

public class FormularioTelaDTO extends TelaDTO<ItensTelaFormulario> {

    private BotaoTela botaoOk;
    private BotaoTela botaoCancelar;

    public FormularioTelaDTO(TipoTela tipoTela, String titulo) {
        super(tipoTela, titulo);
    }

    public BotaoTela getBotaoOk() {
        return botaoOk;
    }

    public void setBotaoOk(BotaoTela botaoOk) {
        this.botaoOk = botaoOk;
    }

    public BotaoTela getBotaoCancelar() {
        return botaoCancelar;
    }

    public void setBotaoCancelar(BotaoTela botaoCancelar) {
        this.botaoCancelar = botaoCancelar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FormularioTelaDTO that = (FormularioTelaDTO) o;

        if (!Objects.equals(botaoOk, that.botaoOk)) return false;
        return Objects.equals(botaoCancelar, that.botaoCancelar);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (botaoOk != null ? botaoOk.hashCode() : 0);
        result = 31 * result + (botaoCancelar != null ? botaoCancelar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FormularioTelaDTO{" +
                "botaoOk=" + botaoOk +
                ", botaoCancelar=" + botaoCancelar +
                '}';
    }
}
