package com.example.viajouapi.services;

import com.example.viajouapi.models.Tipo;
import com.example.viajouapi.repositorys.TipoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {
    private final TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    // Buscando todos os tipos
    public List<Tipo> buscarTodosTipos() {
        return tipoRepository.findAll();
    }

    // Buscando pelo nome, ele pode estar em qualquer lugar da linha
    public List<Tipo> buscarTipoPorNome(String nome) {
        return tipoRepository.findByNomeContainsIgnoreCase(nome).orElseThrow(() ->
                new EntityNotFoundException("Nenhum tipo encontrado"));
    }

    // Buscando tipo pelo ID
    public Tipo buscarTipoPorId(Long id) {
        return tipoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Tipo não encontrado"));
    }

    // Salvando e atualizando tipo
    public Tipo salvarTipo(Tipo tipo) {
        return tipoRepository.save(tipo);
    }
}
