package com.trf5.jus.br.sgc.domain.dto.sei;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements Serializable {

    private String idUsuario;

    private String sigla;

    private String nome;


}
