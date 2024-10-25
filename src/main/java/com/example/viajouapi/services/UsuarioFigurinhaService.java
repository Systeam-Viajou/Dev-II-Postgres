package com.example.viajouapi.services;

import com.example.viajouapi.models.UsuarioFigurinha;
import com.example.viajouapi.repositorys.UsuarioFigurinhaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioFigurinhaService {
    private final UsuarioFigurinhaRepository usuarioFigurinhaRepository;

    public UsuarioFigurinhaService(UsuarioFigurinhaRepository usuarioFigurinhaRepository) {
        this.usuarioFigurinhaRepository = usuarioFigurinhaRepository;
    }

    // Buscando todas as figurinha
    public List<UsuarioFigurinha> buscarFigurinhas() {
        return usuarioFigurinhaRepository.findAll();
    }

    // Buscando figurinha pelo id
    public UsuarioFigurinha buscarFigurinhaPorID(Long id) {
        return usuarioFigurinhaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Figurinha não encontrada com ID: " + id));
    }

    // Salvando e atualizando figurinha
    public UsuarioFigurinha salvarFigurinha(UsuarioFigurinha usuarioFigurinha) {
        return usuarioFigurinhaRepository.save(usuarioFigurinha);
    }

    // Buscando figurinha pelo ID do usuário
    public List<UsuarioFigurinha> buscarFigurinhasPorUsuario(String idUsuario) {
        return usuarioFigurinhaRepository.findByIdUsuario_Uid(idUsuario).orElseThrow(() ->
                new EntityNotFoundException("Nenhuma figurinha encontrada"));
    }
}
