package com.trf5.jus.br.sgc.domain.entity;

import com.trf5.jus.br.sgc.domain.enums.TipoApostilamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "apostilamento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apostilamento{


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    private String numeroContrato;
    private String numeroPA;
    private String numeroPAD;

    @Enumerated(EnumType.STRING)
    private TipoApostilamento tipo;

    private String descricao;
    private Integer exercicio;

    private LocalDateTime dataCriacao;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario;

    @OneToMany(mappedBy = "apostilamento")
    private List<ApostilamentoObjeto> objetos;

    public boolean validarExercicio() { return true; }
}
