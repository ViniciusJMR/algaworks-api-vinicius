package com.algaworks.algalog.apivinicius.domain.service;

import com.algaworks.algalog.apivinicius.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.apivinicius.domain.exception.NegocioException;
import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import com.algaworks.algalog.apivinicius.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepo;

    public Entrega buscar(Long entregaId){
        return entregaRepo.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
