package com.example.viajouapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ViajouApiApplication {

    public static void main(String[] args) {
        // Iniciar a aplicação sem carregar o .env
        SpringApplication.run(ViajouApiApplication.class, args);
    }
}
