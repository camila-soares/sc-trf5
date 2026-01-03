package com.trf5.jus.br.sgc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrgaoSarhEnum {

    TRF5("TRF5",0),
    JFPE("JFPE",1030),
    JFPB("JFPB",1031),
    JFRN("JFRN",1032),
    JFCE("JFCE",1033),
    JFAL("JFAL",1034),
    JFSE("JFSE",1035);

    private String orgao;
    private Integer idOrgao;

    private OrgaoSarhEnum(String orgao, Integer idOrgao) {
        this.orgao = orgao;
        this.idOrgao = idOrgao;
    }

    private OrgaoSarhEnum(String orgao) {
        this.orgao = orgao;
    }


    public static Integer obterIdOrgao(String orgao){
        for (OrgaoSarhEnum objOrgao : OrgaoSarhEnum.values()) {
            if(objOrgao.getOrgao().equals(orgao)) return objOrgao.getIdOrgao();
        }

        return null;
    }

    public static String obterOrgao(Integer idOrgao){
        for (OrgaoSarhEnum objOrgao : OrgaoSarhEnum.values()) {
            if(objOrgao.getIdOrgao().equals(idOrgao)) return objOrgao.getOrgao();
        }

        return null;
    }



}
