package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "O nome não pode exceder 255 caracteres.")
    @Column(name = "nome", length = 255)
    private String nome;

    @Size(max = 255, message = "O site da empresa não pode exceder 255 caracteres.")
    @Column(name = "site_empresa", length = 255)
    private String siteEmpresa;

    @Column(name = "data_desativacao")
    private LocalDateTime dataDesativacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiteEmpresa() {
        return siteEmpresa;
    }

    public void setSiteEmpresa(String siteEmpresa) {
        this.siteEmpresa = siteEmpresa;
    }

    public LocalDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(LocalDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}

