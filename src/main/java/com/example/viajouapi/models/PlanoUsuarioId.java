//package com.example.viajouapi.models;
//import java.io.Serializable;
//import java.util.Objects;
//
//public class PlanoUsuarioId implements Serializable {
//    private Long planoId;
//    private String usuarioId;
//
//    // Construtores, getters, setters, hashCode e equals
//    public PlanoUsuarioId() {}
//
//    public PlanoUsuarioId(Long planoId, String usuarioId) {
//        this.planoId = planoId;
//        this.usuarioId = usuarioId;
//    }
//
//    // Getters e setters
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PlanoUsuarioId that = (PlanoUsuarioId) o;
//        return Objects.equals(planoId, that.planoId) && Objects.equals(usuarioId, that.usuarioId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(planoId, usuarioId);
//    }
//}
