package com.example.viajouapi.services;

import com.example.viajouapi.models.Plano;
import com.example.viajouapi.repositorys.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    // Buscando todos os planos
    public List<Plano> buscarPlanos() {
        return planoRepository.findAll();
    }

    // Buscando plano pelo id
    public Plano buscarPlanoPorID(Long id) {
        return planoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Plano não encontrado com ID: " + id));
    }

    // Salvando e atualizando plano
    public Plano salvarPlano(Plano plano) {
        return planoRepository.save(plano);
    }

    // Opcional: você pode adicionar outros métodos personalizados, como buscar por nome, etc.
}
