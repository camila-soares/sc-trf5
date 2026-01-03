package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "item_contrato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemContrato {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    private String descricao;

    private String ufExecucao;

    private Integer quantidadePrevista;

    private BigDecimal valorUnitario;

    private Integer quantidadeExecutada;

    private BigDecimal valorExecutado;
}
