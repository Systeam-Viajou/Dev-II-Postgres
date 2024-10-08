package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "excursao")
public class Excursao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 10, message = "A capacidade não pode exceder 10 caracteres.")
    @Column(name = "capacidade", length = 10)
    private String capacidade;

    @Size(max = 10, message = "A quantidade de pessoas não pode exceder 10 caracteres.")
    @Column(name = "qntd_pessoas", length = 10)
    private String quantidadePessoas;

    @DecimalMin(value = "0.00", message = "O preço total deve ser no mínimo 0.00.")
    @DecimalMax(value = "99999999.99", message = "O preço total deve ser no máximo 99999999.99.")
    @Column(name = "preco_total", precision = 10, scale = 2)
    private BigDecimal precoTotal;

    @Column(name = "data_inicio", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataInicio;

    @Column(name = "data_termino", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataTermino;

    @NotNull(message = "A atração é obrigatória.")
    @ManyToOne
    @JoinColumn(name = "ID_atracao", referencedColumnName = "id", nullable = false)
    private Atracao atracao;

    @ManyToOne
    @JoinColumn(name = "ID_empresa", referencedColumnName = "id")
    private Empresa empresa;

    @Column(name = "data_desativacao")
    private ZonedDateTime dataDesativacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(String quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public ZonedDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(ZonedDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public ZonedDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(ZonedDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Atracao getAtracao() {
        return atracao;
    }

    public void setAtracao(Atracao atracao) {
        this.atracao = atracao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ZonedDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(ZonedDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}

