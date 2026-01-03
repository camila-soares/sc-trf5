package com.trf5.jus.br.sgc.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum SecaoEnum {

    TRF5("0", "T5", "TRF5"),
    JFAL("3", "AL", "JFAL"),
    JFCE("5", "CE", "JFCE"),
    JFPB("2", "PB", "JFPB"),
    JFPE("1", "PE", "JFPE"),
    JFRN("6", "RN", "JFRN"),
    JFSE("4", "SE", "JFSE");

    public String codigo;
    public String sigla;
    public String orgao;

}
