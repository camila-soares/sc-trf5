package com.trf5.jus.br.sgc.domain.entity;


import com.trf5.jus.br.sgc.domain.enums.TipoGestao;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "gestor")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gestor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    private String nome;
    private String cargo;
    private TipoGestao tipoGestao;
}
