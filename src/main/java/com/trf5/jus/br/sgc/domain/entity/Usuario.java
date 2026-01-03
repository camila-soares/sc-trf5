package com.trf5.jus.br.sgc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Comparable<Usuario> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;


    private String login;
    private String nome;
    private Boolean ativo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orgao_id")
    private OrgaoUsuario orgao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PermissaoUsuario> permissoes = new ArrayList<>();

    @Column(name = "senha")
    private String senha;
    //@Transient
    @Column(name = "token") 
    private String token;

   // @Transient
    private String roles[];


    public void adicionarPermissoes(String[] permissoes) {
        for (String permissao : permissoes) {
            PermissaoUsuario permisaoUsuario = new PermissaoUsuario();
            permisaoUsuario.setUsuario(this);
            if (permissao != null) {
                permisaoUsuario.setPerfil(permissao);
                this.permissoes.add(permisaoUsuario);
            }
        }

    }

    @Override
    public int compareTo(Usuario o) {
        return this.id.compareTo(o.id);
    }
}
