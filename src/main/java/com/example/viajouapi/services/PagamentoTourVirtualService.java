//package com.example.viajouapi.services;
//
//import com.example.viajouapi.models.PagamentoTourVirtual;
//import com.example.viajouapi.models.PlanoUsuario;
//import com.example.viajouapi.repositorys.PagamentoTourVirtualRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PagamentoTourVirtualService {
//    private final PagamentoTourVirtualRepository pagamentoTourVirtualRepository;
//
//    public PagamentoTourVirtualService(PagamentoTourVirtualRepository pagamentoTourVirtualRepository) {
//        this.pagamentoTourVirtualRepository = pagamentoTourVirtualRepository;
//    }
//
//    // Buscando todos os pagamentos
//    public List<PagamentoTourVirtual> buscarPagamentos() {
//        return pagamentoTourVirtualRepository.findAll();
//    }
//
//    // Buscando pagamento pelo id
//    public PagamentoTourVirtual buscarPagamentoPorID(Long id) {
//        return pagamentoTourVirtualRepository.findById(id).orElseThrow(() ->
//                new RuntimeException("Pagamento não encontrado com ID: " + id));
//    }
//
//    // Salvando e atualizando pagamento
//    public PagamentoTourVirtual salvarPagamento(PagamentoTourVirtual pagamentoTourVirtual) {
//        return pagamentoTourVirtualRepository.save(pagamentoTourVirtual);
//    }
//
//    // Buscando pagamentos pelo ID do usuário
//    public List<PlanoUsuario> buscarPagamentosPorUsuario(String uid) {
//        return pagamentoTourVirtualRepository.findByUsuarioUid(uid);
//    }
//}
