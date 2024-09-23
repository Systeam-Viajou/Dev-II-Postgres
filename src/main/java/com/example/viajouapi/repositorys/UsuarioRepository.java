package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByUid(String uid);
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
}
