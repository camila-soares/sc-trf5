package com.trf5.jus.br.sgc.domain.dto;


import com.trf5.jus.br.sgc.domain.entity.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class UsuarioResumoDTO {

    private Integer id;
    private String secao;
    private String login;
    private String nome;
    private Boolean ativo;
    private String roles[];
    private String unidades[];
    private String nomeLogin;


    public UsuarioResumoDTO(Usuario usuario) {
        this.id = Math.toIntExact(usuario.getId());
        this.secao = usuario.getOrgao().getDescricaoOrgao();
        this.login = usuario.getLogin();
        this.nome  = usuario.getNome();
        this.ativo = usuario.getAtivo();
        this.nomeLogin = createNomeLogin(this.nome, this.login);

        List<String> permissoes = new ArrayList<String>();
        usuario.getPermissoes().stream().forEach(permissao -> permissoes.add(permissao.getPerfil()));
        this.roles = permissoes.toArray(String[]::new);


        List<String> unidades = new ArrayList<String>();
        if (usuario.getOrgao() != null && !usuario.getOrgao().getUnidades().isEmpty()) {
            usuario.getOrgao().getUnidades().stream().forEach(unidade -> unidades.add(unidade.getSiglaUnidade()));
        }
        this.unidades = unidades.toArray(String[]::new);
    }

    public static Page<UsuarioResumoDTO> converter(Page<Usuario> usuario) {
        return usuario.map(UsuarioResumoDTO::new);
    }

    public String createNomeLogin(String nome, String login) {
        return nome + " ("+login+")";
    }


}
