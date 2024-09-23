package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "uid", nullable = false, length = 255)
    private String uid;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 100)
    private String sobrenome;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past // Garantindo que a data seja no passado
    private Date dataNascimento;

    @Column(name = "nickname", nullable = false, length = 30, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    @Email // Validação de formato de e-mail
    private String email;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "genero", nullable = false, length = 1)
    @Pattern(regexp = "F|M|N|O") // Validação para aceitar apenas 'F', 'M' ou 'N'
    private String genero;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;



    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

//    @ManyToOne
//    @JoinColumn(name = "ID_role", nullable = false)
//    private Role role;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
}