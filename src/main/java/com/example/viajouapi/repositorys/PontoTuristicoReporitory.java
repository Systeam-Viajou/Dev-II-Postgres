package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PontoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PontoTuristicoReporitory extends JpaRepository<PontoTuristico, Long> {
    Optional<PontoTuristico> findByAtracao_Id(Long id);
}
