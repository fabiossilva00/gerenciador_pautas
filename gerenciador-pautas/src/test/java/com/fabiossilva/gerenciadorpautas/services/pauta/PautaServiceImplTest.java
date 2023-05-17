package com.fabiossilva.gerenciadorpautas.services.pauta;

import com.fabiossilva.gerenciadorpautas.constants.TelaConsts;
import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.models.tela.ItensTelaSelecao;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.models.tela.TipoTela;
import com.fabiossilva.gerenciadorpautas.repositories.PautaRepository;
import com.fabiossilva.gerenciadorpautas.utils.ApplicationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PautaServiceImplTest {

    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private ApplicationUtils appUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.pautaService = new PautaServiceImpl(appUtils, pautaRepository);
    }

    @Test
    void findAllVazio() {
        Mockito.when(pautaRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertTrue(pautaService.findAll().isEmpty());
    }

    @Test
    void criaTelaPautaDTO_ValoresTela() {
        List<Pauta> pautas = Arrays.asList(new Pauta(1L, 1L, "Teste Pauta", ""));
        List<ItensTelaSelecao> itensTela = Arrays.asList(new ItensTelaSelecao("Teste Pauta", "url"));

        Mockito.when(pautaRepository.findAll()).thenReturn(pautas);
        SelecaoTelaDTO selecaoTelaDTO = pautaService.criaTelaSelecaoPautaDTO();

        //Tipo Tela
        Assertions.assertEquals(TipoTela.SELECAO, selecaoTelaDTO.getTipoTela());
        //Titulo Tela
        Assertions.assertEquals(TelaConsts.tituloPauta, selecaoTelaDTO.getTitulo());
        //Tipo classe
        Assertions.assertInstanceOf(ItensTelaSelecao.class, selecaoTelaDTO.getItens().get(0));
    }

    @Test
    void criaTelaPautaDTO_PautaVazio() {
        Mockito.when(pautaRepository.findAll()).thenReturn(new ArrayList<>());
        SelecaoTelaDTO selecaoTelaDTO = pautaService.criaTelaSelecaoPautaDTO();
        //
        Assertions.assertTrue(selecaoTelaDTO.getItens().isEmpty());
    }

    @Test
    void criaTelaPautaDTO_PautaNull() {
        Mockito.when(pautaRepository.findAll()).thenReturn(new ArrayList<>());
        SelecaoTelaDTO selecaoTelaDTO = pautaService.criaTelaSelecaoPautaDTO();
        //
        Assertions.assertTrue(selecaoTelaDTO.getItens().isEmpty());
    }


}
