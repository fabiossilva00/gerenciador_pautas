package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.constants.TelaConsts;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.services.pauta.PautaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PautaControllerTest {

    @Value(value = "${local.server.port}")
    private int port;
    @Value(value = "${hostname}")
    private String hostname;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Mock
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void telaSelecaoPauta_Status200() throws Exception {
        var telaPauta = new SelecaoTelaDTO(TelaConsts.tituloPauta);
        Mockito.when(pautaService.criaTelaPautaDTO()).thenReturn(telaPauta);
        ResponseEntity<String> response = this.testRestTemplate.exchange("http://localhost:" + port + "/v1/pauta/tela", HttpMethod.GET, null, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}