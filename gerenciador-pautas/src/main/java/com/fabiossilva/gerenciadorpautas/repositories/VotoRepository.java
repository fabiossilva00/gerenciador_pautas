package com.fabiossilva.gerenciadorpautas.repositories;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.Voto;
import com.fabiossilva.gerenciadorpautas.entities.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK> {
}
