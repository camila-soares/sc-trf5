package com.trf5.jus.br.sgc.domain.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContratoApostilhamentoDTO {
    private Long id;
    private Long contractId;
    private String processSei;
    private String type;
    private String description;
    private BigDecimal valueChange;
    private BigDecimal valueAfter;
    private LocalDate date;
    private String representative;
    private Long documentId;
}

