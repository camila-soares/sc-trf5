package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unidade-tecnica")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeTecnica extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String codigoUnidade;

    @Column(nullable = false, length = 200)
    private String descricao;

    private String codigoSuperior;

    private String ramal;

}
