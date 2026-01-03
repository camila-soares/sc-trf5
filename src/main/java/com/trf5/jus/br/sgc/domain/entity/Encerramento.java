package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "encerramento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Encerramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

//    @Enumerated(EnumType.STRING)
//    private MotivoEncerramento motivo;

    private LocalDate dataEncerramento;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private LocalDateTime dataCriacao;

    public void finalizarContrato() {}

}
