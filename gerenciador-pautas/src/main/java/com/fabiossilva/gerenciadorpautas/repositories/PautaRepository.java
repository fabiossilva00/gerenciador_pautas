package com.fabiossilva.gerenciadorpautas.repositories;

import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
