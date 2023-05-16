package com.fabiossilva.gerenciadorpautas.repositories;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.votos.Voto;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotoPK;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotosCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK> {

    @Query(value = "SELECT valor, COUNT(*) FROM voto WHERE sessao_id = :sessaoId GROUP BY valor", nativeQuery = true)
    List<VotosCount> countVotosPorSessao(@Param("sessaoId") Long sessaoId);

    Boolean existsByIdSessaoIdAndIdCpf(Sessao sessao, String cpf);

}
