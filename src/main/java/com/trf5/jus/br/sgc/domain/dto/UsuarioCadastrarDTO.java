package com.trf5.jus.br.sgc.domain.dto;


import com.trf5.jus.br.sgc.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCadastrarDTO {

    private String secao;
    private String login;
    private String nome;
    private String roles[];


    public UsuarioCadastrarDTO(Usuario usuario) {
        this.secao = usuario.getOrgao().getDescricaoOrgao();
        this.login = usuario.getLogin();
        this.nome  = usuario.getNome();
        this.roles = usuario.getRoles();
    }

}
