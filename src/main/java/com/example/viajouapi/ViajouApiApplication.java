package com.example.viajouapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ViajouApiApplication {

    public static void main(String[] args) {
        // Carregar o arquivo .env
        Dotenv dotenv = Dotenv.configure().load();

        // Definir variáveis de ambiente programaticamente
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

        SpringApplication.run(ViajouApiApplication.class, args);
    }

}
