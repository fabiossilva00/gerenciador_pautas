package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.services.sessao.SessaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sessao")
public class SessaoController {
    private static final Logger logger = LoggerFactory.getLogger(SessaoController.class);

    @Autowired
    private SessaoService sessaoService;

    @PostMapping()
    public ResponseEntity criarSessaoVotacao(@Valid @RequestBody SessaoDTO sessaoDTO) {
        try {
            logger.info("criando sessao");
            final SessaoDTO sessao = sessaoService.criarSessaoVotacao(sessaoDTO);
            return new ResponseEntity<>(sessao, HttpStatus.CREATED);
        } catch (NotFoundException ex) {
            return ResponseEntity.unprocessableEntity().body(ex.getErrorResponse());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is ok");
    }

}
