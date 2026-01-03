package com.trf5.jus.br.sgc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@Entity
@Table(name="orgaousuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrgaoUsuario implements Comparable<OrgaoUsuario> {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    private Integer codigoOrgao;
    private String descricaoOrgao;

    @ManyToMany
    @JoinTable(name = "orgaousuario_unidades", joinColumns = @JoinColumn(name = "orgao_usuario_id"), inverseJoinColumns = @JoinColumn(name = "unidades_id"))
    private List<Unidade> unidades = new ArrayList<>();

    @Override
    public int compareTo(OrgaoUsuario o) {
        return this.id.compareTo(o.getId());
    }

}
