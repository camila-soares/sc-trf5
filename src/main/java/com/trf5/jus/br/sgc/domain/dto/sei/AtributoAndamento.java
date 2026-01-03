package com.trf5.jus.br.sgc.domain.dto.sei;

import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtributoAndamento implements Serializable {

    private String nome;
    private String valor;
    private String idOrigem;

}
