package com.fabiossilva.gerenciadorpautas.services.voto;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.votos.Voto;
import com.fabiossilva.gerenciadorpautas.entities.votos.VotoPK;
import com.fabiossilva.gerenciadorpautas.exceptions.BusinessException;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
import com.fabiossilva.gerenciadorpautas.models.StatusCpfDTO;
import com.fabiossilva.gerenciadorpautas.models.VotoDTO;
import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;
import com.fabiossilva.gerenciadorpautas.repositories.VotoRepository;
import com.fabiossilva.gerenciadorpautas.services.UserInfoService;
import com.fabiossilva.gerenciadorpautas.services.sessao.SessaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private static final Logger logger = LoggerFactory.getLogger(VotoServiceImpl.class);

    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;

    private final UserInfoService userInfoService;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository, SessaoService sessaoService, UserInfoService userInfoService) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
        this.userInfoService = userInfoService;
    }

    @Override
    @Transactional
    public void votarNaSessao(@NotNull VotoDTO votoDTO) throws NotFoundException, SessaoException {
        final Optional<Sessao> sessaoOptional = sessaoService.findById(votoDTO.getIdSessao());
        if (!sessaoOptional.isPresent()) {
            logger.info("sessão não encontrada com id: {}", votoDTO.getIdSessao());
            final var error = new ErrorResponse("Sessão não encontrada", Map.of("idSessao", "ID da sessão incorreto"));
            throw new NotFoundException("Sessão não encontrada", error);
        }
        
        final Sessao sessao = sessaoOptional.get();

        try {
            final StatusCpfDTO statusCpfDTO = userInfoService.checkCPFVoto(votoDTO.getCpf());
            if (!statusCpfDTO.getStatus().verificaStatus()) {
                final var error = new ErrorResponse("CPF não habilitado para voto", Map.of("cpf", "inválido para votação"));
                throw new BusinessException("CPF não habilitado para voto", error);
            }
        } catch (NotFoundException ex) {
            final var error = new ErrorResponse("CPF inválido", Map.of("cpf", "inválido"));
            throw new BusinessException("CPF inválido", error);
        }

        if (votoRepository.existsByIdSessaoIdAndIdCpf(sessao, votoDTO.getCpf())) {
            logger.info("voto na sessao {} com o mesmo CPF ", sessao.getId());
            final var error = new ErrorResponse("CPF já votou na sessão", Map.of("cpf", "cpf existente com voto na sessão"));
            throw new SessaoException("CPF já votou na sessão", error);
        }

        try {
            sessaoService.inserirVotosNaSessao(sessao, votoDTO.getVoto());
            votoRepository.saveAndFlush(buildVoto(votoDTO, sessao));
            logger.info("Voto computado na sessão: {}", sessao.getId());
        } catch (SessaoException ex) {
            throw ex;
        }
    }

    private Voto buildVoto(@NotNull VotoDTO votoDTO, @NotNull Sessao sessao) {
        final var votoPK = new VotoPK();
        votoPK.setCpf(votoDTO.getCpf());
        votoPK.setSessaoId(sessao);
        votoPK.setValor(votoDTO.getVoto());
        final var voto = new Voto(votoPK);
        return voto;
    }

}
