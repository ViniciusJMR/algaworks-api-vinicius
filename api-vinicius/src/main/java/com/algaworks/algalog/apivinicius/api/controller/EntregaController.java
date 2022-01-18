package com.algaworks.algalog.apivinicius.api.controller;

import com.algaworks.algalog.apivinicius.api.EntregaAssembler;
import com.algaworks.algalog.apivinicius.api.input.EntregaInput;
import com.algaworks.algalog.apivinicius.api.model.EntregaModel;
import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import com.algaworks.algalog.apivinicius.domain.repository.EntregaRepository;
import com.algaworks.algalog.apivinicius.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private SolicitacaoEntregaService entregaService;
    @Autowired
    private EntregaRepository entregaRepo;
    @Autowired
    private EntregaAssembler entregaAssembler;

    public EntregaController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput){
        return entregaAssembler.toModel(entregaService.solicitar(entregaAssembler.toEntity(entregaInput)));
    }

    @GetMapping
    public List<EntregaModel> listar(){
        return entregaAssembler.toCollectionModel(entregaRepo.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
        return entregaRepo.findById(entregaId)
                .map(entrega -> {
                    return ResponseEntity.ok(entregaAssembler.toModel(entrega));
                }).orElse(ResponseEntity.notFound().build());
    }
}
