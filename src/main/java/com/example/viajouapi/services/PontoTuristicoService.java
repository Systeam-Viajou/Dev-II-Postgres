package com.example.viajouapi.services;

import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.repositorys.PontoTuristicoReporitory;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // Salvando e atualizando os pontos turisticos
    public PontoTuristico salvarPontoTuristico(PontoTuristico pontoTuristico){
        return pontoTuristicoReporitory.save(pontoTuristico);
    }
}
