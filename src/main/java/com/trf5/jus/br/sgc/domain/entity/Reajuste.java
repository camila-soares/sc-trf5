package com.trf5.jus.br.sgc.domain.entity;


import com.trf5.jus.br.sgc.domain.enums.IndiceAplicado;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reajuste_contrato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reajuste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    @Enumerated(EnumType.STRING)
    private IndiceAplicado indiceAplicado;


    private LocalDate dataBase;

    private BigDecimal valorAnterior;
    private BigDecimal valorAjustado;
}
