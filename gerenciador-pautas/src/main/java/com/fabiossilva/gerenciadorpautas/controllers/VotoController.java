package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.exceptions.BusinessException;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.exceptions.SessaoException;
import com.fabiossilva.gerenciadorpautas.models.VotoDTO;
import com.fabiossilva.gerenciadorpautas.services.UserInfoService;
import com.fabiossilva.gerenciadorpautas.services.voto.VotoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/voto")
public class VotoController {

    private static final Logger logger = LoggerFactory.getLogger(VotoController.class);

    @Autowired
    private VotoService votoService;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping()
    public ResponseEntity votar(@Valid @RequestBody VotoDTO votoDTO) {
        try {
            logger.info("iniciando votação");
            votoService.votarNaSessao(votoDTO);
            return ResponseEntity.ok().build();
        } catch (NotFoundException | SessaoException | BusinessException ex) {
            return ResponseEntity.unprocessableEntity().body(ex.getErrorResponse());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is ok");
    }

}
