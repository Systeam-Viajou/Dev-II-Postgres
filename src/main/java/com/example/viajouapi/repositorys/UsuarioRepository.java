package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUid(String uid);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
}
