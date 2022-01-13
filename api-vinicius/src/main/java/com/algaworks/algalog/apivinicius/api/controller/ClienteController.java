package com.algaworks.algalog.apivinicius.api.controller;

import com.algaworks.algalog.apivinicius.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        var c1 = new Cliente();
        c1.setId(1L);
        c1.setName("João");
        c1.setEmail("joao@gmail.com");
        c1.setPhone("aaaaaaaaa");

        return Arrays.asList(c1, c1);
    }
}
