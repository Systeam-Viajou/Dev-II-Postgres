package com.example.viajouapi.services;

import com.example.viajouapi.models.Empresa;
import com.example.viajouapi.repositorys.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    private EmpresaService(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    // Buscando todas as empresas
    public List<Empresa> buscarEmpresa(){
        return empresaRepository.findAll();
    }

    // Buscando as empresas pelo id
    public Empresa buscarEmpresaPorID(Long id){
        return empresaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Empresa não encontrado"));
    }

    // Salvando e atualizando as empresas
    public Empresa salvarEmpresa(Empresa empresa){
        return empresaRepository.save(empresa);
    }
}
