package com.fabiossilva.gerenciadorpautas.services.pauta;

import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.models.PautaDTO;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;

import java.util.List;

public interface PautaService {

    List<Pauta> findAll();

    SelecaoTelaDTO criaTelaSelecaoPautaDTO();

    PautaDTO salvarPauta(PautaDTO pauta);
}
