package com.trf5.jus.br.sgc.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Perfil {

    ADMIN("ADMIN", "Administrador"),
    CONS("GER", "Consultar"),
    RADMIN("RADMIN", "Representante de Administração"),
    RUT("RUT", "Representante de UT"),
    SUP("TI", "Suporte");

    private String perfil;
    private String nome;

}
