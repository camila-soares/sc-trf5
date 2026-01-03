package com.trf5.jus.br.sgc.domain.entity;


import com.trf5.jus.br.sgc.domain.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fornecedor")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor  extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipo;
    @Column(nullable = false, length = 200)
    private String nome;
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;
    private String endereco;
    private String cep;
    private String telefone;
    private String email;
    private String nomeRepesentante;
    private String representanteRole;
}
