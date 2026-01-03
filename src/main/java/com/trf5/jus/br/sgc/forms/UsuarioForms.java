package com.trf5.jus.br.sgc.forms;

import com.trf5.jus.br.sgc.domain.entity.OrgaoUsuario;
import com.trf5.jus.br.sgc.domain.entity.PermissaoUsuario;
import com.trf5.jus.br.sgc.domain.entity.Usuario;
import jakarta.persistence.criteria.*;
import lombok.NoArgsConstructor;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForms {


    private String nomeLogin;

    private Boolean ativo;

    private String orgao;

    private List<String> permissoes;

    public Specification<Usuario> toSpec() {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(this.nomeLogin)) {
                Path<String> loginField = root.get("login");
                Path<String> nomeField = root.get("nome");

                Predicate predicateLogin = criteriaBuilder.like(
                        criteriaBuilder.upper(loginField), "%"+this.nomeLogin.toUpperCase()+"%");
                Predicate predicateNome = criteriaBuilder.like(
                        criteriaBuilder.upper(nomeField), "%"+this.nomeLogin.toUpperCase()+"%");

                Predicate predicateNomeLogin = criteriaBuilder.or(predicateLogin, predicateNome);

                predicates.add(predicateNomeLogin);
            }

            if (StringUtils.hasText(this.orgao)) {
                Join<Usuario, OrgaoUsuario> joinUsuarioOrgaoUsuario = root.join("orgao");
                Path<String> descricaoOrgaoField = joinUsuarioOrgaoUsuario.get("descricaoOrgao");
                Predicate predicateOrgao = criteriaBuilder.like(
                        criteriaBuilder.upper(descricaoOrgaoField), "%"+this.orgao.toUpperCase()+"%");
                predicates.add(predicateOrgao);
            }

            if (this.ativo != null) {
                Path<Integer> ativoField = root.get("ativo");
                Predicate predicateAtivo = criteriaBuilder.equal(ativoField, this.ativo);
                predicates.add(predicateAtivo);
            }

            if (this.permissoes != null && this.permissoes.size() > 0) {
                Path<Integer> idField = root.get("id");

                // Criando as referencias
                Subquery<List> sub = criteriaQuery.subquery(List.class);
                Root<PermissaoUsuario> subRoot = sub.from(PermissaoUsuario.class);

                // Join com Usuario
                Join<PermissaoUsuario, Usuario> joinPermissaoUsuarioUsuario = subRoot.join("usuario");

                // criando consulta
                sub.select(subRoot.get("perfil"));

                // criando as condições
                List<Predicate> subPredicates = new ArrayList<>();
                subPredicates.add(criteriaBuilder.equal(joinPermissaoUsuarioUsuario.get("id"), idField)); // Se o id de PermissaoUsuario.id_usuario = usuario.id
                subPredicates.add(criteriaBuilder.in(subRoot.get("perfil")).value(this.permissoes)); // se PermissaoUsuario.perfil in (PERTENTE) a this.permissoes

                sub.where(criteriaBuilder.and(subPredicates.toArray(new Predicate[0]))); // Adicionado as condicoes a subconsulta

                Predicate predicate = criteriaBuilder.exists(sub);

                predicates.add(predicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
