package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
