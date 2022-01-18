package com.algaworks.algalog.apivinicius.api.controller;

import com.algaworks.algalog.apivinicius.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.apivinicius.api.input.OcorrenciaInput;
import com.algaworks.algalog.apivinicius.api.model.OcorrenciaModel;
import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import com.algaworks.algalog.apivinicius.domain.model.Ocorrencia;
import com.algaworks.algalog.apivinicius.domain.service.BuscaEntregaService;
import com.algaworks.algalog.apivinicius.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar (@PathVariable Long entregaId,
                                      @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
        Ocorrencia ocorrenciaRegistrar = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrar);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
       Entrega entrega = buscaEntregaService.buscar(entregaId);

       return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
