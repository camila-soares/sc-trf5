package com.trf5.jus.br.sgc.domain.dto.sei;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Andamento implements Serializable {

    private String idAndamento;
    private String idTarefa;
    private String idTarefaModulo;
    private String descricao;
    private String dataHora;
    private Unidade unidade;
    private Usuario usuario;

    private AtributoAndamento[] atributos;
}
