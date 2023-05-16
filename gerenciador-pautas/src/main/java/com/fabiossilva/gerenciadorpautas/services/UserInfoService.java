package com.fabiossilva.gerenciadorpautas.services;

import com.fabiossilva.gerenciadorpautas.exceptions.GenericException;
import com.fabiossilva.gerenciadorpautas.exceptions.NotFoundException;
import com.fabiossilva.gerenciadorpautas.models.StatusCpfDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class UserInfoService {

    @Value(value = "${urlCPF}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    public StatusCpfDTO checkCPFVoto(String cpf) throws NotFoundException {
        try {
            final var headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            var entity = new HttpEntity<>(headers);
            final String url = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment(cpf).build().toString();
            logger.info("Iniciando comunicacao na url {}", url);
            final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response != null && response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                System.out.println(response.getBody());
                final StatusCpfDTO statusCpfDTO = objectMapper.readValue(response.getBody(), StatusCpfDTO.class);
//                logger.info("Comunicacao realizada com sucesso {}", body != null ? body.toString() : "");
                logger.info("Comunicacao realizada com sucesso {}");
                return statusCpfDTO;
            } else {
                throw new GenericException("Ocorreu um erro ao adquirir status CPF para voto");
            }

        } catch (HttpClientErrorException ex) {
            if (HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw new NotFoundException("CPF não válido");
            }
            ex.printStackTrace();
            throw new GenericException("Ocorreu um erro ao adquirir status CPF para voto");
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new GenericException("Ocorreu um erro ao adquirir status CPF para voto");
        }
    }

}
