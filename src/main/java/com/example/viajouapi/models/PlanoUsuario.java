//package com.example.viajouapi.models;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import java.time.ZonedDateTime;
//
//@Entity
//@Table(name = "plano_usuario")
//@IdClass(PlanoUsuarioId.class)
//public class PlanoUsuario {
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "ID_plano", referencedColumnName = "id", nullable = false)
//    private Plano planoId;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "ID_usuario", referencedColumnName = "uid", nullable = false)
//    private Usuario usuarioId;
//
//    @NotNull(message = "Data de pagamento é obrigatória")
//    @Column(name = "data_pagamento")
//    private ZonedDateTime dataPagamento;
//
//    @NotNull(message = "Data de término é obrigatória")
//    @Column(name = "data_termino")
//    private ZonedDateTime dataTermino;
//
//    // Getters and Setters
//    public Plano getPlanoId() {
//        return planoId;
//    }
//
//    public void setPlanoId(Plano planoId) {
//        this.planoId = planoId;
//    }
//
//    public Usuario getUsuarioId() {
//        return usuarioId;
//    }
//
//    public void setUsuarioId(Usuario usuarioId) {
//        this.usuarioId = usuarioId;
//    }
//
//    public ZonedDateTime getDataPagamento() {
//        return dataPagamento;
//    }
//
//    public void setDataPagamento(ZonedDateTime dataPagamento) {
//        this.dataPagamento = dataPagamento;
//    }
//
//    public ZonedDateTime getDataTermino() {
//        return dataTermino;
//    }
//
//    public void setDataTermino(ZonedDateTime dataTermino) {
//        this.dataTermino = dataTermino;
//    }
//}
