package com.trf5.jus.br.sgc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "objetocontratoexercicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjetoContratoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(nullable = false)
  //  private Integer ano;

  //  @Column(nullable = false)
  //  private String local;

    private String valor;
    private String exercicio;

    @ManyToOne
    @JoinColumn(name = "objeto_id")
    private ObjetoContrato objeto;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
}