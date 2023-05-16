package com.fabiossilva.gerenciadorpautas.services.voto;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.entities.votos.Voto;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import com.fabiossilva.gerenciadorpautas.models.VotoDTO;
import com.fabiossilva.gerenciadorpautas.repositories.VotoRepository;
import com.fabiossilva.gerenciadorpautas.services.UserInfoService;
import com.fabiossilva.gerenciadorpautas.services.sessao.SessaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VotoServiceImplTest {

    private VotoService votoService;
    private VotoDTO votoDTO;
    private Sessao sessao;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private UserInfoService userInfoService;

    VotoServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.votoService = new VotoServiceImpl(votoRepository, sessaoService, userInfoService);
        this.votoDTO = new VotoDTO(1L, TipoVoto.SIM, "25541538092");
        this.sessao = buildSessao();
    }

    @Test
    void votarNaSessao_SessaoInvalida() {
        when(sessaoService.findById(any(Long.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> votoService.votarNaSessao(votoDTO));
    }

    @Test
    void votarNaSessao_VotoComMesmoCPF() {
        when(sessaoService.findById(any(Long.class))).thenReturn(Optional.of(this.sessao));
        when(votoRepository.existsByIdSessaoIdAndIdCpf(any(Sessao.class), any(String.class))).thenReturn(true);

        Assertions.assertThrows(NotFoundException.class, () -> votoService.votarNaSessao(votoDTO));
    }

    @Test
    void votarNaSessao_SessaoExption() {
        when(sessaoService.findById(any(Long.class))).thenReturn(Optional.of(this.sessao));
        when(votoRepository.existsByIdSessaoIdAndIdCpf(any(Sessao.class), any(String.class))).thenReturn(false);
        doThrow(new SessaoException()).when(sessaoService).inserirVotosNaSessao(any(Sessao.class), any(TipoVoto.class));

        Assertions.assertThrows(SessaoException.class, () -> votoService.votarNaSessao(votoDTO));
    }

    @Test
    void votarNaSessao() {
        when(sessaoService.findById(any(Long.class))).thenReturn(Optional.of(this.sessao));
        when(votoRepository.existsByIdSessaoIdAndIdCpf(any(Sessao.class), any(String.class))).thenReturn(false);

        votoService.votarNaSessao(votoDTO);

        verify(sessaoService).inserirVotosNaSessao(this.sessao, votoDTO.getVoto());
        verify(votoRepository).saveAndFlush(any(Voto.class));
    }

    private Sessao buildSessao() {
        final var s = new Sessao();
        s.setId(1L);
        return s;
    }

}