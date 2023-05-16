package com.fabiossilva.gerenciadorpautas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GerenciadorPautasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorPautasApplication.class, args);
    }

}
