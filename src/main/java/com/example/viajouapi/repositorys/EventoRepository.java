package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.Excursao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Optional<Evento> findByAtracao_Id(Long id);
    @Query(value = "SELECT * FROM mostrar_eventos_recentes()", nativeQuery = true)
    List<Evento> mostrarEventosRecentes();

}
