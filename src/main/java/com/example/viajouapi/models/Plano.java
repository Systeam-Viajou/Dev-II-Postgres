package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @NotNull(message = "O nome é obrigatório")
    @Column(length = 50)
    private String nome;

    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    private String descricao;

    @NotNull(message = "O campo livrePropaganda é obrigatório")
    @Column(name = "livre_propaganda", nullable = false)
    private Boolean livrePropaganda = false;

    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser positivo")
    @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 dígitos inteiros e 2 decimais")
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Size(max = 20, message = "A duração deve ter no máximo 20 caracteres")
    private String duracao;

    @Column(name = "data_desativacao")
    private LocalDateTime dataDesativacao;

    // Getters e Setters

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getLivrePropaganda() {
        return livrePropaganda;
    }

    public void setLivrePropaganda(Boolean livrePropaganda) {
        this.livrePropaganda = livrePropaganda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public LocalDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(LocalDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}

