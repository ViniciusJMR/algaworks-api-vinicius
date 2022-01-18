package com.algaworks.algalog.apivinicius.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Problema {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Campo {
        private String nome;
        private String mensagem;
    }

}
