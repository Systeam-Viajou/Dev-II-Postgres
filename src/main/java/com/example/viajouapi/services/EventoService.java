package com.example.viajouapi.services;

import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.repositorys.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    private EventoService(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    // Buscando todos os eventos
    public List<Evento> buscarEvento(){
        return eventoRepository.findAll();
    }

    // Buscando os eventos pelo id
    public Evento buscarEventoPorID(Long id){
        return eventoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Evento não encontrado"));
    }

    // Salvando e atualizando os eventos
    public Evento salvarEvento(Evento evento){
        return eventoRepository.save(evento);
    }
}
