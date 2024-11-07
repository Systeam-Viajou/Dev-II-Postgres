package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Optional<Evento> findByAtracao_Id(Long id);
}
