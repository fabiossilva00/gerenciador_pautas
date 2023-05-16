package com.fabiossilva.gerenciadorpautas.services.sessao;

import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotosCount;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotosJSONB;
import com.fabiossilva.gerenciadorpautas.exceptions.GenericException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;
import com.fabiossilva.gerenciadorpautas.repositories.PautaRepository;
import com.fabiossilva.gerenciadorpautas.repositories.SessaoRepository;
import com.fabiossilva.gerenciadorpautas.repositories.VotoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SessaoServiceImpl implements SessaoService {

    private static final Logger logger = LoggerFactory.getLogger(SessaoServiceImpl.class);

    private SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;
    private VotoRepository votoRepository;

    @Autowired
    public SessaoServiceImpl(SessaoRepository sessaoRepository, PautaRepository pautaRepository, VotoRepository votoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.votoRepository = votoRepository;
    }

    @Override
    public SessaoDTO criarSessaoVotacao(@NotNull SessaoDTO sessaoDTO) throws GenericException {
        final Optional<Pauta> optionalPauta = pautaRepository.findById(sessaoDTO.getIdPauta());
        if (!optionalPauta.isPresent()) {
            logger.info("pauta não encontrada com id: {}", sessaoDTO.getIdPauta());
            final var error = new ErrorResponse("Pauta não encontrada", Map.of("idPauta", "ID de pauta incorreto"));
            throw new GenericException("Pauta não encontrada", error);
        }

        logger.info("criando sessao - {}", sessaoDTO);
        final Pauta pauta = optionalPauta.get();
        final Sessao sessaoOptional = sessaoRepository.findByPauta(pauta).orElse(null);
        if (sessaoOptional != null && !sessaoOptional.getClosed()) {
            return buildSessaoDTOResponse(sessaoOptional);
        }

        final var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setCreatedAt(LocalDateTime.now());
        sessao.setEndsIn(LocalDateTime.now().plusMinutes(sessaoDTO.getTempoEmAberto()));
        sessao.setVotos(new VotosJSONB(0, 0));
        final Sessao sessaoNew = sessaoRepository.saveAndFlush(sessao);
        logger.info("sessao criada com id: {}", sessaoNew.getId());
        return buildSessaoDTOResponse(sessaoNew);
    }

    private SessaoDTO buildSessaoDTOResponse(@NotNull Sessao sessao) {
        final var sessaoDTO = new SessaoDTO();
        sessaoDTO.setId(sessao.getId());
        sessaoDTO.setIdPauta(sessao.getPauta().getId());
        sessaoDTO.setCriadoEm(sessao.getCreatedAt());
        sessaoDTO.setClosed(sessao.getClosed());

        Long tempoAberto = sessao.getCreatedAt().until(sessao.getEndsIn(), ChronoUnit.MINUTES);
        sessaoDTO.setTempoEmAberto(tempoAberto.intValue());
        return sessaoDTO;
    }

    @Transactional
    @Scheduled(cron = "0 */1 * * * *")
    private void fechaSessoesEmAberto() {
        logger.info("EXECUTANDO CRON - FECHAR SESSOES EM ABERTO");
        final List<Sessao> sessoes = sessaoRepository.findAllByClosedAndEndsInLessThan(false, LocalDateTime.now());
        sessoes.stream().parallel().forEach(s -> {
            List<VotosCount> votos = votoRepository.countVotosPorSessao(s.getId());
            final var votosJSONB = new VotosJSONB();
            votos.forEach(v -> {
                if (v.getValor().equals(TipoVoto.SIM.toString())) {
                    votosJSONB.setSIM(v.getCount());
                } else {
                    votosJSONB.setNAO(v.getCount());
                }
            });
            s.setVotos(votosJSONB);
            s.setClosed(true);
            sessaoRepository.saveAndFlush(s);
            logger.info("SESSAO ENCERRADA ID: {}", s.getId());
        });
    }
}
