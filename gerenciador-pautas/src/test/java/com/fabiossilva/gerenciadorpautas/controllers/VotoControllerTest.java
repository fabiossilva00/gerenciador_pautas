package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.entities.Sessao;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import com.fabiossilva.gerenciadorpautas.models.VotoDTO;
import com.fabiossilva.gerenciadorpautas.services.voto.VotoService;
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

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoService votoService;


    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        final var sessao = new Sessao();
        sessao.setId(1L);
    }

    @Test
    void votar_Status400() throws Exception {
        mockMvc.perform(post("/v1/voto")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new SessaoDTO())))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    void votar_Status415() throws Exception {
        mockMvc.perform(post("/v1/voto")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new SessaoDTO())))
                .andExpect(status().isUnsupportedMediaType()).andDo(print());
    }

    @Test
    void votar_Status422_SessaoNaoEncontrada() throws Exception {
        final var votoDTO = new VotoDTO(1L, TipoVoto.SIM, "25541538092");
        doThrow(new NotFoundException()).when(votoService).votarNaSessao(votoDTO);

        mockMvc.perform(post("/v1/voto")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(votoDTO)))
                .andExpect(status().isUnprocessableEntity()).andDo(print());
    }

    @Test
    void votar_Status200() throws Exception {
        final var votoDTO = new VotoDTO(1L, TipoVoto.SIM, "25541538092");

        mockMvc.perform(post("/v1/voto")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(votoDTO)))
                .andExpect(status().isOk()).andDo(print());
    }

}