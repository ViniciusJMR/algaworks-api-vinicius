package com.algaworks.algalog.apivinicius.api.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {
    @NotNull
    private Long clienteId;

    @NotNull
    @Valid
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;
}
