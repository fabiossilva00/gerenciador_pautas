package com.fabiossilva.gerenciadorpautas.services.pauta;

import com.fabiossilva.gerenciadorpautas.constants.ApplicationConsts;
import com.fabiossilva.gerenciadorpautas.constants.TelaConsts;
import com.fabiossilva.gerenciadorpautas.entities.Pauta;
import com.fabiossilva.gerenciadorpautas.models.tela.ItensTelaSelecao;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.repositories.PautaRepository;
import com.fabiossilva.gerenciadorpautas.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private ApplicationUtils appUtils;

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    @Override
    public SelecaoTelaDTO criaTelaPautaDTO() {
        List<Pauta> pautas = findAll();
        var selecaoTela = new SelecaoTelaDTO(TelaConsts.tituloPauta);
        selecaoTela.setItens(buildItensPauta(pautas));
        return selecaoTela;
    }

    private List<ItensTelaSelecao> buildItensPauta(List<Pauta> pautas) {
        var itens = new ArrayList<ItensTelaSelecao>();
        pautas.stream().forEach(p -> {
            String url = appUtils.buildUrlBotao(ApplicationConsts.PATH_PAUTA) + p.getId();
            itens.add(new ItensTelaSelecao(p.getNome(), url));
        });
        return itens;
    }


}
