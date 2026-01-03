package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/** Nota de Empenho (1..N por contrato). */
@Entity
@Table(name = "empenho")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empenho extends BaseEntity  {


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contrato contrato;

    @Column(nullable = false, length = 50)
    private String numero;

    private LocalDate data;
    private String planoInterno;
    private String numeroPAD;
    private String fonte;
    private BigDecimal valor;

}

