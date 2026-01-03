package com.trf5.jus.br.sgc.domain.dto.sei;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeCompletoDTO {

    private Integer id;
    private Integer idUnidadeGestora;
    private String siglaUnidadeGestora;
    private Integer idUnidadeSGO;
    private String siglaUnidade;
    private String nomeUnidade;
    private Boolean unidadeTecnica;
    private Boolean unidadeTi;
    private Boolean ativa;
    private String emailUnidade;


}
