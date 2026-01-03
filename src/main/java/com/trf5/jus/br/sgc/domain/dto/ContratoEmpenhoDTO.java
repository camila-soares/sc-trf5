package com.trf5.jus.br.sgc.domain.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContratoEmpenhoDTO {

    private Long id;
    private Long contractId;
    private String number;
    private BigDecimal value;
    private LocalDate issueDate;
}
