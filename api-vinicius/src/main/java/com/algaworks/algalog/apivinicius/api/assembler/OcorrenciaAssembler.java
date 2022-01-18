package com.algaworks.algalog.apivinicius.api.assembler;

import com.algaworks.algalog.apivinicius.api.model.OcorrenciaModel;
import com.algaworks.algalog.apivinicius.common.ModelMapperConfig;
import com.algaworks.algalog.apivinicius.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

    private ModelMapper modelMappel;

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
        return modelMappel.map(ocorrencia,OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
