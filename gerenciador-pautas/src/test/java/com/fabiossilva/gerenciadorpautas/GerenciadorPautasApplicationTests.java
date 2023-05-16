package com.fabiossilva.gerenciadorpautas;

import com.fabiossilva.gerenciadorpautas.controllers.PautaController;
import com.fabiossilva.gerenciadorpautas.controllers.SessaoController;
import com.fabiossilva.gerenciadorpautas.controllers.VotoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GerenciadorPautasApplicationTests {

    @Autowired
    private PautaController pautaController;

    @Autowired
    private SessaoController sessaoController;

    @Autowired
    private VotoController votoController;

    @Test
    void contextLoads() {

        Assertions.assertNotNull(pautaController);
        Assertions.assertNotNull(sessaoController);
        Assertions.assertNotNull(votoController);
    }

}
