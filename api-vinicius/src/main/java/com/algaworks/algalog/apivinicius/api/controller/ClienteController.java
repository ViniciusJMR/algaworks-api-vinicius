package com.algaworks.algalog.apivinicius.api.controller;

import com.algaworks.algalog.apivinicius.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
}