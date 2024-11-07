package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PesquisaPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PesquisaPerfilRepository extends JpaRepository<PesquisaPerfil, Long> {
    Optional<PesquisaPerfil> findByUsuario_Uid(String uid);
}
