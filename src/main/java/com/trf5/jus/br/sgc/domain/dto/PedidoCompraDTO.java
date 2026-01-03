package com.trf5.jus.br.sgc.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCompraDTO {

    private Integer numeroPedigo;
    private Integer valor;
    private String numeroPad;
    private String numeroProcesso;
    private String tipoDespesa;
    private String tipoAquisicao;
    private String prazoEntrega;
    private String dataEmissao;
    private String situacao;
    private String localEntrega;
    private String criteriaoJulgamento;
    private String prazoValidadeGarantia;
    private Boolean cotacaoGerada;
    private Boolean despesaIndireta;
    private Boolean empenhoGerado;
    private Boolean ordemFornecimentoGerada;
    private Boolean mapaComparativoGerado;
    private List<ItemPedidoCompraDTO> itens;

}
