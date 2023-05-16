package com.fabiossilva.gerenciadorpautas.services.voto;

import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.models.VotoDTO;

public interface VotoService {

    void votarNaSessao(VotoDTO votoDTO) throws NotFoundException;
}
