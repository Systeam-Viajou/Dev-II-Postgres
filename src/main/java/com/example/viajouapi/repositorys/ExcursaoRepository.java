package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Excursao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExcursaoRepository extends JpaRepository<Excursao, Long> {
    @Query(value = "SELECT * FROM mostrar_excursoes_recentes()", nativeQuery = true)
    List<Excursao> mostrarExcursoesRecentes();
}
