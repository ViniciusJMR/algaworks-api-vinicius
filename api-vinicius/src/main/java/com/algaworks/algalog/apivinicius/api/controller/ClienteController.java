package com.algaworks.algalog.apivinicius.api.controller;

import com.algaworks.algalog.apivinicius.domain.model.Cliente;
import com.algaworks.algalog.apivinicius.domain.repository.ClienteRepository;
import com.algaworks.algalog.apivinicius.domain.service.CatalogoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CatalogoClienteService clienteService;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId)
                //.map(cliente -> ResponseEntity.ok(cliente))
                .map(ResponseEntity::ok) //Method Reference
                .orElse(ResponseEntity.notFound().build());

        /*
        Forma maior de se fazer o uqe está acima
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent())
            return ResponseEntity.ok(cliente.get());

        return ResponseEntity.notFound().build();
         */
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteId))
            return ResponseEntity.notFound().build();
        cliente.setId(clienteId);
        return ResponseEntity.ok(clienteService.salvar(cliente));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteId){
        if (!clienteRepository.existsById(clienteId))
            return ResponseEntity.notFound().build();
        clienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }
}
