package com.trf5.jus.br.sgc.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoResponse {

    //private Integer id;
    private String numeroContrato;

    private FornecedorDTO fornecedor;
  //  private String fornecedorCnpj;

    private BigDecimal valorTotal;
    private BigDecimal valorMensal;

    private LocalDate dataAssinatura;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    private Integer prazoMeses;
    private String responsavel;

    private String unidadeTecnica;
    private String status;

    private long diasRestantes;

    private String observacoes;
}
