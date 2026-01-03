package com.trf5.jus.br.sgc.domain.dto.sei;


import com.trf5.jus.br.sgc.domain.entity.OrgaoUsuario;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetornoConsultarUsuario {

    private String id;
    private String login;
    private String matricula;
    private String Idorgao;
    private String nome;
    private String ativo;


}
