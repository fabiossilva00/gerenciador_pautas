package com.fabiossilva.gerenciadorpautas.repositories;

import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Optional<Sessao> findByPauta(Pauta pauta);

    
    List<Sessao> findAllByClosedAndEndsInLessThan(Boolean closed, LocalDateTime endsIn);
}
