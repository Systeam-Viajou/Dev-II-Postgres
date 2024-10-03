package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @NotNull(message = "O UID não pode ser nulo")
    @Size(min = 1, message = "O UID não pode estar vazio")
    @Column(name = "uid", nullable = false, length = 255)
    private String uid;

    @NotNull(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotNull(message = "O sobrenome não pode ser vazio")
    @Size(min = 3, max = 100, message = "O sobrenome deve ter entre 3 e 100 caracteres")
    @Column(name = "sobrenome", nullable = false, length = 100)
    private String sobrenome;

    @NotNull(message = "A data de nascimento não pode ser vazia")
    @Past(message = "A data de nascimento deve ser no passado")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @NotNull(message = "O nome de usuário não pode ser vazio")
    @Size(min = 3, max = 30, message = "O nome de usuário deve ter entre 3 e 30 caracteres")
    @Column(name = "nickname", nullable = false, length = 30, unique = true)
    private String username;

    @NotNull(message = "O email não pode ser vazio")
    @Email(message = "Formato de email inválido")
    @Size(max = 255, message = "O email deve ter no máximo 255 caracteres")
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @NotNull(message = "O telefone não pode ser vazio")
    @Size(min = 11, max = 11, message = "O telefone deve conter exatamente 11 dígitos")
    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @NotNull(message = "O gênero não pode ser vazio")
    @Pattern(regexp = "F|M|N|O", message = "O gênero deve ser 'F', 'M', 'N' ou 'O'")
    @Column(name = "genero", nullable = false, length = 1)
    private String genero;

    @NotNull(message = "O CPF não pode ser vazio")
    @CPF(message = "O CPF informado é inválido")
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @NotNull(message = "A senha não pode ser vazia")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    // Getters e Setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
