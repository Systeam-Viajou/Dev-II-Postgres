package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUid(String uid);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);

    @Procedure(name = "atualizar_nome")
    void atualizarNome(
            @Param("v_uid_usuario") String uidUsuario,
            @Param("v_nickname") String nickname
    );

    @Procedure(name = "cadastro_usuario")
    void cadastroUsuario(
            @Param("v_uid_usuario") String uidUsuario,
            @Param("v_nickname") String nickname,
            @Param("v_nome") String nome,
            @Param("v_sobrenome") String sobrenome,
            @Param("v_cpf") String cpf,
            @Param("v_email") String email,
            @Param("v_data_nascimento") String dataNascimento,
            @Param("v_telefone") String telefone,
            @Param("v_genero") Character genero,
            @Param("v_senha") String senha
    );
}
