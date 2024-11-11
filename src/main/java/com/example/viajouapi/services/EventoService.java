package com.example.viajouapi.services;

import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.Excursao;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.repositorys.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    // Buscando todos os eventos
    public List<Evento> buscarEvento(){
        return eventoRepository.mostrarEventosRecentes();
    }

    // Buscando eventos com paginação
    public List<Evento> buscarEventoPaginacao(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return eventoRepository.findAll(pageable).getContent();
    }

    // Buscando os eventos pelo id
    public Evento buscarEventoPorID(Long id){
        return eventoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Evento não encontrado"));
    }

    // Salvando e atualizando os eventos
    public Evento salvarEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    public Evento buscarPorAtracao(Long id){
        return eventoRepository.findByAtracao_Id(id).orElseThrow(() ->
                new EntityNotFoundException("Evento não encontrado"));
    }
}
