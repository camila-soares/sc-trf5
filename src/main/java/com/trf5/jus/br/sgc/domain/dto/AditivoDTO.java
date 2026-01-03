package com.trf5.jus.br.sgc.domain.dto;


import com.trf5.jus.br.sgc.domain.enums.TipoAditivo;
import com.trf5.jus.br.sgc.domain.enums.UnidadePrazo;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AditivoDTO {

    private Long id;
    private Long contratoId;

   // private String numero;
    private String numeroPA;
    private String numeroPAD;

    private LocalDate dataAssinatura;

    private TipoAditivo tipo;
    private String descricao;

    private LocalDate dataInicio;
    private String vigenteAte;

    private BigDecimal valorTotal;
    private BigDecimal percentual;

    private String demanda;

    private Integer prazo;
    private UnidadePrazo unidadePrazo;

    private BigDecimal valorReajuste;

    private String prazoDevolvido;

    private LocalDate novaVigencia;
}
