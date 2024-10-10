package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PlanoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanoUsuarioRepository extends JpaRepository<PlanoUsuario, Long> {
    // Método para buscar por ID_plano e ID_usuario
    Optional<PlanoUsuario> findByPlanoIdAndUsuarioId(Long planoId, String usuarioId);
}
