package com.example.viajouapi.controllers;

import com.example.viajouapi.models.PagamentoTourVirtual;
import com.example.viajouapi.services.PagamentoTourVirtualService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/pagamentos")
public class PagamentoTourVirtualController {

    private final PagamentoTourVirtualService pagamentoTourVirtualService;

    public PagamentoTourVirtualController(PagamentoTourVirtualService pagamentoTourVirtualService) {
        this.pagamentoTourVirtualService = pagamentoTourVirtualService;
    }

    @Operation(summary = "Buscar todos os pagamentos", description = "Retorna uma lista de todos os pagamentos de tour virtual cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamentos recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<PagamentoTourVirtual> buscarPagamentos() {
        return pagamentoTourVirtualService.buscarPagamentos();
    }

    @Operation(summary = "Buscar pagamento por ID", description = "Busca um pagamento de tour virtual pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento recuperado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PagamentoTourVirtual> buscarPagamentoPorID(@PathVariable Long id) {
        try {
            PagamentoTourVirtual pagamento = pagamentoTourVirtualService.buscarPagamentoPorID(id);
            return ResponseEntity.ok(pagamento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Inserir um novo pagamento", description = "Insere um novo pagamento de tour virtual no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirPagamento(@Valid @RequestBody PagamentoTourVirtual pagamento, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            pagamentoTourVirtualService.salvarPagamento(pagamento);
            return ResponseEntity.ok("Pagamento inserido com sucesso");
        }
    }

    @Operation(summary = "Atualizar um pagamento existente", description = "Atualiza as informações de um pagamento de tour virtual pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarPagamento(
            @PathVariable Long id,
            @Valid @RequestBody PagamentoTourVirtual pagamento,
            BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }

        try {
            PagamentoTourVirtual pagamentoExistente = pagamentoTourVirtualService.buscarPagamentoPorID(id);
            pagamentoExistente.setIdUsuario(pagamento.getIdUsuario());
            pagamentoExistente.setIdTourVirtual(pagamento.getIdTourVirtual());
            pagamentoExistente.setDataPagamento(pagamento.getDataPagamento());

            pagamentoTourVirtualService.salvarPagamento(pagamentoExistente);
            return ResponseEntity.ok("Pagamento atualizado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado");
        }
    }
}

