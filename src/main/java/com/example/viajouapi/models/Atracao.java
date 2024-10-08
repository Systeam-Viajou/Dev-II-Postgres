package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "atracao")
public class Atracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da atração é obrigatório.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Size(max = 255, message = "O endereço não pode exceder 255 caracteres.")
    @Column(name = "endereco", length = 255)
    private String endereco;

    @Column(name = "acessibilidade", nullable = false)
    private Boolean acessibilidade = false;

    @DecimalMin(value = "0.00", message = "A classificação média deve ser no mínimo 0.00.")
    @DecimalMax(value = "5.00", message = "A classificação média deve ser no máximo 5.00.")
    @Column(name = "media_classificacao", precision = 3, scale = 2)
    private BigDecimal mediaClassificacao;

    @ManyToOne
    @JoinColumn(name = "ID_categoria", referencedColumnName = "id")
    private Categoria categoria;

    @Column(name = "data_desativacao")
    private ZonedDateTime dataDesativacao;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Boolean acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public BigDecimal getMediaClassificacao() {
        return mediaClassificacao;
    }

    public void setMediaClassificacao(BigDecimal mediaClassificacao) {
        this.mediaClassificacao = mediaClassificacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ZonedDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(ZonedDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}
