package com.algaworks.algalog.apivinicius.domain.service;

import com.algaworks.algalog.apivinicius.domain.exception.NegocioException;
import com.algaworks.algalog.apivinicius.domain.model.Cliente;
import com.algaworks.algalog.apivinicius.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CatalogoClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepo.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.getEmail().equals(cliente));

        if(emailEmUso)
            throw new NegocioException("Já existe um cliente cadastrado com esse email");
        return clienteRepo.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepo.deleteById(clienteId);
    }
}
