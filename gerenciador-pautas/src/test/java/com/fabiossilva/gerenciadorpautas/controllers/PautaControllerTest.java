package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.constants.TelaConsts;
import com.fabiossilva.gerenciadorpautas.models.PautaDTO;
import com.fabiossilva.gerenciadorpautas.models.tela.SelecaoTelaDTO;
import com.fabiossilva.gerenciadorpautas.services.pauta.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PautaService pautaService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void telaSelecaoPauta_Status200() throws Exception {
        final var telaPauta = new SelecaoTelaDTO(TelaConsts.tituloPauta);
        when(pautaService.criaTelaPautaDTO()).thenReturn(telaPauta);
        mockMvc.perform(get("/v1/pauta/tela")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void criarPauta_Status400() throws Exception {
        mockMvc.perform(post("/v1/pauta")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PautaDTO())))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    void criarPauta_Status415() throws Exception {
        mockMvc.perform(post("/v1/pauta")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PautaDTO())))
                .andExpect(status().isUnsupportedMediaType()).andDo(print());
    }

    @Test
    void criarPauta_Status200() throws Exception {
        final var pauta = new PautaDTO(1L, 1L, "Teste Pauta", null);
        final var pautaRequest = new PautaDTO();
        pautaRequest.setNome("Teste Pauta");
        pautaRequest.setAssociadoId(1L);
        when(pautaService.salvarPauta(any(PautaDTO.class))).thenReturn(pauta);

        mockMvc.perform(post("/v1/pauta")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pautaRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(pauta.getId().intValue())))
                .andDo(print());
    }

}