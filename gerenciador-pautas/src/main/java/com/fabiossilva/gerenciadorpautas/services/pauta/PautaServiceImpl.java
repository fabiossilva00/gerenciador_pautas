package com.fabiossilva.gerenciadorpautas.services.pauta;

import com.fabiossilva.gerenciadorpautas.constants.ApplicationConsts;
import com.fabiossilva.gerenciadorpautas.constants.TelaConsts;
import com.fabiossilva.gerenciadorpautas.controllers.SessaoController;
import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.models.PautaDTO;
import com.fabiossilva.gerenciadorpautas.models.tela.ItensTelaSelecao;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.repositories.PautaRepository;
import com.fabiossilva.gerenciadorpautas.utils.ApplicationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    private static final Logger logger = LoggerFactory.getLogger(SessaoController.class);

    private final ApplicationUtils appUtils;
    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(ApplicationUtils appUtils, PautaRepository pautaRepository) {
        this.appUtils = appUtils;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    @Override
    public SelecaoTelaDTO criaTelaPautaDTO() {
        final List<Pauta> pautas = findAll();
        final var selecaoTela = new SelecaoTelaDTO(TelaConsts.tituloPauta);
        selecaoTela.setItens(buildItensPauta(pautas));
        return selecaoTela;
    }

    @Override
    public PautaDTO salvarPauta(PautaDTO pautaDTO) {
        final var p = pautaRepository.saveAndFlush(buildNovaPauta(pautaDTO));
        logger.info("pauta criada com id: {}", p.getId());
        pautaDTO.setId(p.getId());
        return pautaDTO;
    }

    private List<ItensTelaSelecao> buildItensPauta(List<Pauta> pautas) {
        final var itens = new ArrayList<ItensTelaSelecao>();
        if (pautas != null) {
            pautas.forEach(p -> {
                String url = appUtils.buildUrlBotao(ApplicationConsts.PATH_PAUTA) + p.getId();
                itens.add(new ItensTelaSelecao(p.getNome(), url));
            });
        }
        return itens;
    }

    private Pauta buildNovaPauta(PautaDTO pautaDTO) {
        final var p = new Pauta();
        p.setNome(pautaDTO.getNome());
        p.setAssociadoId(pautaDTO.getAssociadoId());
        p.setDescricao(pautaDTO.getDescricao());
        return p;
    }

}
