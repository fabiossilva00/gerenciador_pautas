package com.fabiossilva.gerenciadorpautas.services.sessao;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotosJSONB;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;

import java.util.Optional;

public interface SessaoService {

    SelecaoTelaDTO criaTelaSelecaoSessaoDTO();

    SessaoDTO criarSessaoVotacao(SessaoDTO sessaoDTO) throws NotFoundException;

    void inserirVotosNaSessao(Sessao sessao, TipoVoto voto) throws NotFoundException, SessaoException;

    VotosJSONB quantiaVotosSessao(Long idSessao);

    Optional<Sessao> findById(Long idSessao);
}
