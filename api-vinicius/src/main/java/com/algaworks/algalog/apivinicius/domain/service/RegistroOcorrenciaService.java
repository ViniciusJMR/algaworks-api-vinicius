package com.algaworks.algalog.apivinicius.domain.service;

import com.algaworks.algalog.apivinicius.domain.exception.NegocioException;
import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import com.algaworks.algalog.apivinicius.domain.model.Ocorrencia;
import com.algaworks.algalog.apivinicius.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;
    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
       Entrega entrega = buscaEntregaService.buscar(entregaId);

       return entrega.addOcorrencia(descricao);
    }
}
