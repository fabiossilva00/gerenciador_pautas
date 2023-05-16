package com.fabiossilva.gerenciadorpautas.services.sessao;

import com.fabiossilva.gerenciadorpautas.exceptions.GenericException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;

public interface SessaoService {

    SessaoDTO criarSessaoVotacao(SessaoDTO sessaoDTO) throws GenericException;
}
