package com.example.viajouapi.services;

import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.repositorys.PontoTuristicoReporitory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoTuristicoService {
    private final PontoTuristicoReporitory pontoTuristicoReporitory;

    private PontoTuristicoService(PontoTuristicoReporitory pontoTuristicoReporitory){
        this.pontoTuristicoReporitory = pontoTuristicoReporitory;
    }

    // Buscando todos os pontos turisticos
    public List<PontoTuristico> buscarPontoTuristico(){
        return pontoTuristicoReporitory.findAll();
    }

    // Buscando os pontos turistipos pelo id
    public PontoTuristico buscarPontoPorID(Long id){
        return pontoTuristicoReporitory.findById(id).orElseThrow(() ->
                new RuntimeException("Ponto turídtico não encontrado"));
    }

    // Salvando e atualizando os pontos turisticos
    public PontoTuristico salvarPOntoTuristico(PontoTuristico pontoTuristico){
        return pontoTuristicoReporitory.save(pontoTuristico);
    }
}
