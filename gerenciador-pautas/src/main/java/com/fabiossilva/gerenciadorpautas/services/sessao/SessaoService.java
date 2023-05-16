package com.fabiossilva.gerenciadorpautas.services.sessao;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;

import java.util.Optional;

public interface SessaoService {

    SessaoDTO criarSessaoVotacao(SessaoDTO sessaoDTO) throws NotFoundException;

    void inserirVotosNaSessao(Sessao sessao, TipoVoto voto) throws NotFoundException, SessaoException;

    Optional<Sessao> findById(Long idSessao);
}
