package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.models.PautaDTO;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.services.pauta.PautaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

    @Autowired
    private PautaService pautaService;

    @GetMapping(value = "/tela/selecao")
    public ResponseEntity<SelecaoTelaDTO> telaSelecaoPauta() {
        final SelecaoTelaDTO t = pautaService.criaTelaSelecaoPautaDTO();
        return ResponseEntity.ok(t);
    }

    @PostMapping()
    public ResponseEntity<PautaDTO> criarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        logger.info("criandoPauta");
        final PautaDTO p = pautaService.salvarPauta(pautaDTO);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is ok");
    }

}
