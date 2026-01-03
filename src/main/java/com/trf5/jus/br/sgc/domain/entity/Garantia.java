package com.trf5.jus.br.sgc.domain.entity;


import com.trf5.jus.br.sgc.domain.enums.TipoGarantia;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "garantia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Garantia {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    @Enumerated(EnumType.STRING)
    private TipoGarantia modalidade;

    private BigDecimal valor;
    private BigDecimal percentual;

    private String empresa;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private LocalDateTime dataCriacao;

    public boolean verificarVencimento() { return LocalDate.now().isAfter(dataFim); }
}
