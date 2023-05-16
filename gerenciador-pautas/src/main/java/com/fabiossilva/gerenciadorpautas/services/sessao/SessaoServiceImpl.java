package com.fabiossilva.gerenciadorpautas.services.sessao;

import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotosCount;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotosJSONB;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
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
    public SessaoDTO criarSessaoVotacao(@NotNull SessaoDTO sessaoDTO) throws NotFoundException {
        final Optional<Pauta> optionalPauta = pautaRepository.findById(sessaoDTO.getIdPauta());
        if (!optionalPauta.isPresent()) {
            logger.info("pauta não encontrada com id: {}", sessaoDTO.getIdPauta());
            final var error = new ErrorResponse("Pauta não encontrada", Map.of("idPauta", "ID de pauta incorreto"));
            throw new NotFoundException("Pauta não encontrada", error);
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

    @Override
    public void inserirVotosNaSessao(@NotNull Sessao sessao, TipoVoto voto) throws NotFoundException, SessaoException {
        final Optional<Sessao> sessaoOptional = sessaoRepository.findById(sessao.getId());
        if (!sessaoOptional.isPresent()) {
            final var error = new ErrorResponse("Sessão não encontrada", Map.of("idSessao", "ID da sessão incorreto"));
            throw new NotFoundException("Sessão não encontrada", error);
        }

        final Sessao sessaoUpdate = sessaoOptional.get();
        if (sessaoUpdate.getClosed()) {
            final var error = new ErrorResponse("Sessão de votos já encerrado", Map.of("idSessao", "ID da sessão incorreto"));
            throw new SessaoException("Sessão de votos já encerrado", error);
        }

        sessaoUpdate.getVotos().votar(voto);
        sessaoRepository.saveAndFlush(sessaoUpdate);
        logger.info("sessao atualizada com sucesso");
    }

    @Override
    public Optional<Sessao> findById(@NotNull Long idSessao) {
        if (idSessao == null) {
            return Optional.empty();
        }
        return sessaoRepository.findById(idSessao);
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
    protected void fechaSessoesEmAberto() {
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
