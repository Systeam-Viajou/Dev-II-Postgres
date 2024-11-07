package com.example.viajouapi.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagamento_tour_virtual")
public class PagamentoTourVirtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_usuario", referencedColumnName = "uid", nullable = false)
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_tourvirtual", referencedColumnName = "id", nullable = false)
    private PontoTuristico idTourVirtual;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PontoTuristico getIdTourVirtual() {
        return idTourVirtual;
    }

    public void setIdTourVirtual(PontoTuristico idTourVirtual) {
        this.idTourVirtual = idTourVirtual;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
