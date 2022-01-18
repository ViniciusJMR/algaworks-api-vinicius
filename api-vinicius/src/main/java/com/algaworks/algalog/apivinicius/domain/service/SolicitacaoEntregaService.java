package com.algaworks.algalog.apivinicius.domain.service;

import com.algaworks.algalog.apivinicius.domain.exception.NegocioException;
import com.algaworks.algalog.apivinicius.domain.model.Cliente;
import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import com.algaworks.algalog.apivinicius.domain.model.StatusEntrega;
import com.algaworks.algalog.apivinicius.domain.repository.ClienteRepository;
import com.algaworks.algalog.apivinicius.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepo;
    @Autowired
    private CatalogoClienteService clienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());
        entrega.setCliente(cliente);
        return entregaRepo.save(entrega);
    }
}
