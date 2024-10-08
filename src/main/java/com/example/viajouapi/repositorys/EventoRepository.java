package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
