package com.trf5.jus.br.sgc.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name="permissaousuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PermissaoUsuario implements  Comparable<PermissaoUsuario>{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JsonBackReference
    private Usuario usuario;
    private String perfil;



    @Override
    public int compareTo(PermissaoUsuario o) {
        return this.id.compareTo(o.id);
    }


}
