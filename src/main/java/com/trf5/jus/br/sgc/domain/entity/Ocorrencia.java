package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ocorrencia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"data\"")
    private LocalDateTime data;

    private String descricao;
    private String usuario;
    private Integer arquivo;
    private String providencia;
    private String login;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
}

