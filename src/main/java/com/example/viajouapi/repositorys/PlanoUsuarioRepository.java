package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PlanoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlanoUsuarioRepository extends JpaRepository<PlanoUsuario, Long> {

    // Buscar planos de um usuário específico
    List<PlanoUsuario> findByUsuarioUid(String uid);

    // Buscar por um plano específico de um usuário
    List<PlanoUsuario> findByPlanoIdAndUsuarioUid(Long planoId, String uid);

    // Buscar todos os planos ativos (com data de término posterior à data atual)
    List<PlanoUsuario> findByDataTerminoAfter(LocalDateTime dataAtual);
}