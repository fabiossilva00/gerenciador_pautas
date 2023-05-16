package com.fabiossilva.gerenciadorpautas.controllers;

import com.fabiossilva.gerenciadorpautas.exceptions.GenericException;
import com.fabiossilva.gerenciadorpautas.models.SessaoDTO;
import com.fabiossilva.gerenciadorpautas.models.errors.ErrorResponse;
import com.fabiossilva.gerenciadorpautas.services.sessao.SessaoService;
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

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoService sessaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void criarSessaoVotacao_Status400() throws Exception {
        mockMvc.perform(post("/v1/sessao")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new SessaoDTO())))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    void criarSessaoVotacao_Status415() throws Exception {
        mockMvc.perform(post("/v1/sessao")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new SessaoDTO())))
                .andExpect(status().isUnsupportedMediaType()).andDo(print());
    }

    @Test
    void criarSessaoVotacao_Status422() throws Exception {
        final var sessaoDTO = new SessaoDTO();
        sessaoDTO.setIdPauta(1L);
        final var error = new ErrorResponse("Pauta não encontrada", Map.of("idPauta", "ID de pauta incorreto"));
        final var pautaNãoEncontrada = new GenericException("Pauta não encontrada", error);
        when(sessaoService.criarSessaoVotacao(any(SessaoDTO.class))).thenThrow(pautaNãoEncontrada);

        mockMvc.perform(post("/v1/sessao")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessaoDTO)))
                .andExpect(status().isUnprocessableEntity()).andDo(print());
    }

    @Test
    void criarSessaoVotacao_Status200_TempoAbertoPadrao() throws Exception {
        final var sessao = new SessaoDTO();
        sessao.setId(1L);
        final var sessaoRequest = new SessaoDTO();
        sessaoRequest.setIdPauta(1L);
        when(sessaoService.criarSessaoVotacao(any(SessaoDTO.class))).thenReturn(sessao);

        mockMvc.perform(post("/v1/sessao")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessaoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(sessao.getId().intValue())))
                .andExpect(jsonPath("$.tempoEmAberto", is(1)))
                .andDo(print());
    }

    @Test
    void criarSessaoVotacao_Status200() throws Exception {
        final var sessao = new SessaoDTO();
        sessao.setId(1L);
        sessao.setTempoEmAberto(10);
        final var sessaoRequest = new SessaoDTO();
        sessaoRequest.setIdPauta(1L);
        sessaoRequest.setTempoEmAberto(10);
        when(sessaoService.criarSessaoVotacao(any(SessaoDTO.class))).thenReturn(sessao);

        mockMvc.perform(post("/v1/sessao")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessaoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(sessao.getId().intValue())))
                .andExpect(jsonPath("$.tempoEmAberto", is(10)))
                .andDo(print());
    }

}
