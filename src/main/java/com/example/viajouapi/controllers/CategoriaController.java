package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;

import com.example.viajouapi.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    // Buscando todas as categorias
    @GetMapping("/buscar")
    public List<Categoria> buscarCategoria(){
        return categoriaService.buscarCategoria();
    }

    // INserindo uma categoria
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirCategoria(@Valid @RequestBody Categoria categoria, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            categoriaService.salvarCategoria(categoria);
            return ResponseEntity.ok("Atração inserido com sucesso");
        }
    }

    // Atrualizando uma parte da categoria
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Categoria categoriaExistente = categoriaService.buscarCategoriaPorID(id);
        if (categoriaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atração não encontrada");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    categoriaExistente.setNome((String) valor);
                    break;
                case "dataDesativacao":
                    categoriaExistente.setDataDesativacao(ZonedDateTime.parse((String) valor));
                    break;
            }
        });

        categoriaService.salvarCategoria(categoriaExistente);
        return ResponseEntity.ok("Atração atualizada com sucesso");
    }
}
