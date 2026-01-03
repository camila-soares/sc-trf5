package com.trf5.jus.br.sgc.domain.enums;

import lombok.Getter;

public enum ModeloContrato {

    PREGAO_ELETRONICO("Contrato decorrente de eletrônico"),
    PREGAO("Contrato decorrente de pregão"),
    INEXIGIBILIDADE("Contrato decorrente de dispensa ou inexigibilidade");

    @Getter
    private final String descricao;
    ModeloContrato(String descricao) {
        this.descricao = descricao;
    }

}
