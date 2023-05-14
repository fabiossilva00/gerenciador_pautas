package com.fabiossilva.gerenciadorpautas.models.tela;

public class SelecaoTelaDTO extends TelaDTO<ItensTelaSelecao> {

    public SelecaoTelaDTO(String titulo) {
        super(TipoTela.SELECAO, titulo);
    }
}
