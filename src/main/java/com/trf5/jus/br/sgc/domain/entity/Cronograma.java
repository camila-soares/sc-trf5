package com.trf5.jus.br.sgc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cronograma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cronograma {

    @Id
    private Integer codcronograma;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    private String login;
}
