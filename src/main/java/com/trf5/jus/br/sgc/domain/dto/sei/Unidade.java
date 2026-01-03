package com.trf5.jus.br.sgc.domain.dto.sei;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trf5.jus.br.sgc.domain.entity.BaseEntity;
import com.trf5.jus.br.sgc.util.Util;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidade")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Unidade extends BaseEntity implements Comparable<Unidade> {

    private String idUnidade;
    private Integer idUnidadeGestora;
    private String siglaUnidadeGestora;

    private Integer idUnidadeSGO;
    private String siglaUnidade;
    private String nomeUnidade;
    private Boolean unidadeTecnica;
    private Boolean ativa;
    private Boolean unidadeTi;
    private String emailUnidade;

    @Override
    public int compareTo(Unidade o) {
        return Util.removerAcentos(this.nomeUnidade).compareToIgnoreCase(Util.removerAcentos(o.getNomeUnidade()));
    }

    public Unidade() {
    }

    public Unidade(@Valid UnidadeCompletoDTO unidade) {
        this.idUnidadeGestora = unidade.getIdUnidadeGestora();
        this.siglaUnidade = unidade.getSiglaUnidade();
        this.nomeUnidade = unidade.getNomeUnidade();
        this.unidadeTecnica = unidade.getUnidadeTecnica();
        this.unidadeTi = unidade.getUnidadeTi();
        this.ativa = unidade.getAtiva();
        this.emailUnidade = unidade.getEmailUnidade();
    }

}
