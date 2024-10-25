package com.example.viajouapi.services;

import com.example.viajouapi.models.Empresa;
import com.example.viajouapi.repositorys.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // Buscar todas as empresas
    public List<Empresa> buscarEmpresas() {
        return empresaRepository.findAll();
    }

    // Buscar empresa por ID
    public Empresa buscarEmpresaPorID(Long id) {
        return empresaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Empresa não encontrada com o ID fornecido."));
    }

    // Salvar uma nova empresa ou atualizar uma existente
    @Transactional
    public Empresa salvarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}