package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.services.pauta.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @GetMapping(value = "/tela")
    public ResponseEntity<SelecaoTelaDTO> telaSelecaoPauta() {
        SelecaoTelaDTO t = pautaService.criaTelaPautaDTO();
        return ResponseEntity.ok(t);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is ok");
    }

}
