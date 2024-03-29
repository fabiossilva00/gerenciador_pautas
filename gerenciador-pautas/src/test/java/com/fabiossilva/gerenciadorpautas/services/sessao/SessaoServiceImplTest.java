package com.fabiossilva.gerenciadorpautas.services.sessao;

import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.exceptions.GenericException;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import com.fabiossilva.gerenciadorpautas.repositories.PautaRepository;
import com.fabiossilva.gerenciadorpautas.repositories.SessaoRepository;
import com.fabiossilva.gerenciadorpautas.repositories.VotoRepository;
import com.fabiossilva.gerenciadorpautas.utils.ApplicationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SessaoServiceImplTest {

    private SessaoService sessaoService;
    @Mock
    private ApplicationUtils appUtils;
    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private VotoRepository votoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.sessaoService = new SessaoServiceImpl(appUtils, sessaoRepository, pautaRepository, votoRepository);
    }

    @Test
    void criarSessaoVotacao_PautaNaoEncontrada() {
        final var sessaoDTORequest = new SessaoDTO();
        sessaoDTORequest.setIdPauta(1L);
        final var pauta = new Pauta(1L, 1L, "Teste Pauta", null);
        final var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setId(1L);
        sessao.setCreatedAt(LocalDateTime.now());
        sessao.setEndsIn(LocalDateTime.now().plusMinutes(1));
        sessao.setClosed(true);
        when(pautaRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        //
        Assertions.assertThrows(GenericException.class, () -> sessaoService.criarSessaoVotacao(sessaoDTORequest));
    }

    @Test
    void criarSessaoVotacao_FechadaComMesmaPauta() {
        final var sessaoDTORequest = new SessaoDTO();
        sessaoDTORequest.setIdPauta(1L);
        final var pauta = new Pauta(1L, 1L, "Teste Pauta", null);
        final var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setId(1L);
        sessao.setCreatedAt(LocalDateTime.now());
        sessao.setEndsIn(LocalDateTime.now().plusMinutes(1));
        sessao.setClosed(true);
        when(pautaRepository.findById(any(Long.class))).thenReturn(Optional.of(pauta));
        when(sessaoRepository.findByPauta(any(Pauta.class))).thenReturn(Optional.of(sessao));
        sessao.setId(2L);
        when(sessaoRepository.saveAndFlush(any(Sessao.class))).thenReturn(sessao);

        final SessaoDTO sessaoDTO = sessaoService.criarSessaoVotacao(sessaoDTORequest);
        //
        Assertions.assertEquals(2L, sessaoDTO.getId());
        Assertions.assertEquals(1, sessaoDTO.getTempoEmAberto());
    }

    @Test
    void criarSessaoVotacao_AbertaMesmaPauta() {
        final var sessaoDTORequest = new SessaoDTO();
        sessaoDTORequest.setIdPauta(1L);
        final var pauta = new Pauta(1L, 1L, "Teste Pauta", null);
        final var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setId(1L);
        sessao.setCreatedAt(LocalDateTime.now());
        sessao.setEndsIn(LocalDateTime.now().plusMinutes(1));
        when(pautaRepository.findById(any(Long.class))).thenReturn(Optional.of(pauta));
        when(sessaoRepository.findByPauta(any(Pauta.class))).thenReturn(Optional.of(sessao));
        when(sessaoRepository.saveAndFlush(any(Sessao.class))).thenReturn(sessao);

        final SessaoDTO sessaoDTO = sessaoService.criarSessaoVotacao(sessaoDTORequest);
        //
        Assertions.assertEquals(1L, sessaoDTO.getId());
        Assertions.assertEquals(1, sessaoDTO.getTempoEmAberto());
    }

    @Test
    void criarSessaoVotacao_NovaPauta() {
        final var sessaoDTORequest = new SessaoDTO();
        sessaoDTORequest.setIdPauta(1L);
        final var pauta = new Pauta(1L, 1L, "Teste Pauta", null);
        final var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setId(1L);
        sessao.setCreatedAt(LocalDateTime.now());
        sessao.setEndsIn(LocalDateTime.now().plusMinutes(1));
        when(pautaRepository.findById(any(Long.class))).thenReturn(Optional.of(pauta));
        when(sessaoRepository.findByPauta(any(Pauta.class))).thenReturn(Optional.empty());
        when(sessaoRepository.saveAndFlush(any(Sessao.class))).thenReturn(sessao);

        final SessaoDTO sessaoDTO = sessaoService.criarSessaoVotacao(sessaoDTORequest);
        //
        Assertions.assertEquals(1L, sessaoDTO.getId());
        Assertions.assertEquals(1, sessaoDTO.getTempoEmAberto());
    }

    @Test
    void inserirVotosNaSessao_SessaoNaoEncontrada() {
        when(sessaoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        final var sessao = new Sessao();
        sessao.setId(1L);

        Assertions.assertThrows(NotFoundException.class, () -> sessaoService.inserirVotosNaSessao(sessao, any(TipoVoto.class)));
    }

    @Test
    void inserirVotosNaSessao_SessaoFechada() {
        final var sessao = new Sessao();
        sessao.setId(1L);
        sessao.setClosed(true);
        when(sessaoRepository.findById(any(Long.class))).thenReturn(Optional.of(sessao));

        Assertions.assertThrows(SessaoException.class, () -> sessaoService.inserirVotosNaSessao(sessao, any(TipoVoto.class)));
    }

    @Test
    void inserirVotosNaSessao() {
        final var sessao = new Sessao();
        sessao.setId(1L);
        sessao.setClosed(false);
        when(sessaoRepository.findById(any(Long.class))).thenReturn(Optional.of(sessao));
        sessaoService.inserirVotosNaSessao(sessao, TipoVoto.SIM);
        verify(sessaoRepository).saveAndFlush(any(Sessao.class));
    }


}