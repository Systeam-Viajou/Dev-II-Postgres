package com.example.viajouapi.services;

import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.Excursao;
import com.example.viajouapi.repositorys.ExcursaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcursaoService {
    private final ExcursaoRepository excursaoRepository;

    public ExcursaoService(ExcursaoRepository excursaoRepository){
        this.excursaoRepository = excursaoRepository;
    }

    // Buscando todos os eventos
    public List<Excursao> buscarExcursao(){
        return excursaoRepository.findAll();
    }

    // Buscando os eventos pelo id
    public Excursao buscarExcursaoPorID(Long id){
        return excursaoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Excursao não encontrado"));
    }

    // Salvando e atualizando os eventos
    public Excursao salvarExcursao(Excursao excursao){
        return excursaoRepository.save(excursao);
    }
}
