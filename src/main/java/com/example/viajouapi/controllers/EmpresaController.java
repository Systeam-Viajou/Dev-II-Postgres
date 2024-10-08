package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Empresa;
import com.example.viajouapi.models.Evento;
import com.example.viajouapi.services.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    private EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    // Buscando todas as empresas
    @GetMapping("/buscar")
    public List<Empresa> buscarEvento(){
        return empresaService.buscarEmpresa();
    }

    // Inserindo uma empresa
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirEvento(@Valid @RequestBody Empresa empresa, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            empresaService.salvarEmpresa(empresa);
            return ResponseEntity.ok("Empresa inserido com sucesso");
        }
    }

    // Atualizando uma parte da empresa
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Empresa empresaExistente = empresaService.buscarEmpresaPorID(id);
        if (empresaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    empresaExistente.setNome((String) valor);
                    break;
                case "siteEmpresa":
                    empresaExistente.setSiteEmpresa((String) valor);
                case "dataDesativacao":
                    empresaExistente.setDataDesativacao(ZonedDateTime.parse((String) valor));
                    break;
            }
        });

        empresaService.salvarEmpresa(empresaExistente);
        return ResponseEntity.ok("Empresa atualizado com sucesso");
    }
}
