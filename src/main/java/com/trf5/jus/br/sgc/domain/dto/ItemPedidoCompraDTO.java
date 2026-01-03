package com.trf5.jus.br.sgc.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Data
public class ItemPedidoCompraDTO {

    private Integer item;

    private BigDecimal quantidade;
    private String unidadeReferencia;
    private String elemento;
    private String classificacao;
    private String descricao;
    private String descricaoComplementar;

}
