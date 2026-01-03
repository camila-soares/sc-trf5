package com.trf5.jus.br.sgc.domain.dto.login;


import com.trf5.jus.br.sgc.domain.entity.Usuario;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioLogadoDTO {

    private String login;
    private String sigla;
    private String email;
    private String token;


}
