package com.algaworks.algalog.apivinicius.domain.service;

import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import com.algaworks.algalog.apivinicius.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepo;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar (Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        entrega.finalizar();
        entregaRepo.save(entrega);
    }
}
