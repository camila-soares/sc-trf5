package com.trf5.jus.br.sgc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orgao")
public class Orgao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sigla;
    private String descricao;
    private boolean sin_ativo;
    private boolean sin_autentica;
    private Integer ordem;
}
