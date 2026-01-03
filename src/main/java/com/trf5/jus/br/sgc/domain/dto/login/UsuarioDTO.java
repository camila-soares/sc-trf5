package com.trf5.jus.br.sgc.domain.dto.login;


import com.trf5.jus.br.sgc.domain.dto.UnidadeTecnicaDTO;
import com.trf5.jus.br.sgc.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String id;
    private String secao;
    private String login;
    private String nome;
    private boolean ativo;
    private String[] roles;
    private String senha;
    private List<UnidadeTecnicaDTO> unidades;
    private String descricaoOrgao;
    private String nomeLogin;



    public UsuarioDTO(Usuario usuario) {
        this.id = String.valueOf(usuario.getId());
        this.secao = usuario.getOrgao().getDescricaoOrgao();
        this.login = usuario.getLogin();
        this.nome  = usuario.getNome();
        this.ativo = usuario.getAtivo();
        this.nomeLogin = createNomeLogin(this.nome, this.login);

        List<String> permissoes = new ArrayList<String>();
        usuario.getPermissoes().forEach(permissao ->
                permissoes.add(permissao.getPerfil()));
        this.roles = permissoes.toArray(String[]::new);

        if (usuario.getOrgao() != null && !usuario.getOrgao().getUnidades().isEmpty()) {

         List<UnidadeTecnicaDTO> unidadesr = new ArrayList<>();
          usuario.getOrgao().getUnidades().forEach(
                  unidade -> unidadesr.add(new UnidadeTecnicaDTO())
          );
          this.unidades = unidadesr;
        }
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuario) {
        return usuario.map(UsuarioDTO::new);
    }

    public String createNomeLogin(String nome, String login) {
        return nome + " ("+login+")";
    }



}
