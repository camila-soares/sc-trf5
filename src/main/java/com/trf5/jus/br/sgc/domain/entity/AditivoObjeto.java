package com.trf5.jus.br.sgc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "aditivo_objeto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AditivoObjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aditivo_id")
    private Aditivo aditivo;

    @ManyToOne
    @JoinColumn(name = "objeto_contrato_id")
    private ObjetoContrato objetoContrato;

    private BigDecimal valorReajuste;
    private BigDecimal valorAtualizado;

    private LocalDateTime dataCriacao;

    public BigDecimal calcularDiferenca() {
        return valorAtualizado.subtract(valorReajuste);
    }
}