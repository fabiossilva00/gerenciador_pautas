package com.fabiossilva.gerenciadorpautas;

import com.fabiossilva.gerenciadorpautas.controllers.PautaController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GerenciadorPautasApplicationTests {

    @Autowired
    private PautaController pautaController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(pautaController);
    }

}
