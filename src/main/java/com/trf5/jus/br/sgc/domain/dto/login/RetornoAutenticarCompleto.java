package com.trf5.jus.br.sgc.domain.dto.login;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class RetornoAutenticarCompleto {


    private Long idSistema;
    private Long idUsuario;
    private String idLogin;
    private String hashAgente;
    private boolean validado;

}
