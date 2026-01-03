package com.trf5.jus.br.sgc.domain.dto;


import com.trf5.jus.br.sgc.domain.dto.sei.RetornoConsultarUsuario;
import com.trf5.jus.br.sgc.util.Util;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetornoConsultarUsuarioDTO {

    private String id;
    private String matricula;
    private String orgao;
    private String login;
    private String nome;
    private String ativo;
    private String nomeLogin;

    public RetornoConsultarUsuarioDTO(RetornoConsultarUsuario usuario){
        this.id = usuario.getId();
        this.matricula = usuario.getMatricula();
        this.orgao = Util.getOrgao(usuario.getIdorgao());
        this.login = usuario.getLogin();
        this.nome = usuario.getNome();
        this.ativo = usuario.getAtivo();
        this.nomeLogin = usuario.getNome() + "(" + usuario.getLogin()+ ")";

    }

    public static  List<RetornoConsultarUsuarioDTO> converter(List<RetornoConsultarUsuario> usuarios){
        return usuarios.stream()
                .map(RetornoConsultarUsuarioDTO::new)
                .collect(Collectors.toList());
    }



}
