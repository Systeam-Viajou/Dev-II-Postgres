package com.example.viajouapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ViajouApiApplication {

    public static void main(String[] args) {
        // Carregar variáveis do .env
        Dotenv dotenv = Dotenv.load();

        // Você pode acessar as variáveis assim
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

        // Iniciar a aplicação
        SpringApplication.run(ViajouApiApplication.class, args);
    }
}
