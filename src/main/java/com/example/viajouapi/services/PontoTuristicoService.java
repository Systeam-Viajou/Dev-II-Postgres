package com.example.viajouapi.services;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.repositorys.PontoTuristicoReporitory;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PontoTuristicoService {
    private final PontoTuristicoReporitory pontoTuristicoReporitory;

    public PontoTuristicoService(PontoTuristicoReporitory pontoTuristicoReporitory){
        this.pontoTuristicoReporitory = pontoTuristicoReporitory;
    }

    // Buscando todos os pontos turisticos
    public List<PontoTuristico> buscarPontoTuristico(){
        return pontoTuristicoReporitory.findAll();
    }

    // Buscando os pontos turistipos pelo id
    public PontoTuristico buscarPontoPorID(Long id){
        return pontoTuristicoReporitory.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Ponto turistico não encontrado"));
    }

    public PontoTuristico buscarPontoTuristicoPorAtracao(Long id){
        return pontoTuristicoReporitory.findByAtracao_Id(id).orElseThrow(() ->
                new EntityNotFoundException("Ponto turistico não encontrado"));
    }

    // Gerando uma lista de 15 pontos turisicos aleatórias
    public List<PontoTuristico> gerarPontosTurisicos() {
        List<PontoTuristico> todosPontosTurisicos = buscarPontoTuristico();
        if (todosPontosTurisicos.isEmpty()) {
            throw new RuntimeException("Nenhum ponto turistico encontrado");
        }

        Random random = new Random();
        List<PontoTuristico> pontosTuristicosAleatorios = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            PontoTuristico pontoAleatorio = todosPontosTurisicos.get(random.nextInt(todosPontosTurisicos.size()));
            pontosTuristicosAleatorios.add(pontoAleatorio);
        }
        return pontosTuristicosAleatorios;
    }

    // Salvando e atualizando os pontos turisticos
    public PontoTuristico salvarPontoTuristico(PontoTuristico pontoTuristico){
        return pontoTuristicoReporitory.save(pontoTuristico);
    }
}
