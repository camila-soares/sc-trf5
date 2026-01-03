package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "objeto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private Integer quantidade;
    private String titulo;
    private String descricao;


    private BigDecimal valor;




// ================================
// REGRAS DE NEGÓCIO
// ================================

    /**
     * Calcula o percentual sobre o valor atual.
     * Exemplo: valor = 1000 e percentual = 5 → retorna 50.00
     */
//    public BigDecimal calcularPercentual(BigDecimal percentual) {
//        if (percentual == null || valorAtualizado == null) {
//            return BigDecimal.ZERO;
//        }
//
//        return valor
//                .multiply(percentual)
//                .divide(BigDecimal.valueOf(100))
//                .setScale(2, BigDecimal.ROUND_HALF_UP);
//    }

    /**
     * Atualiza o valor do objeto aplicando:
     * → um percentual (ex.: 5% de reajuste), ou
     * → definindo um novo valor absoluto.
     * <p>
     * Apenas um dos parâmetros deve ser preenchido.
     */
//    public BigDecimal atualizarValor(BigDecimal percentual, BigDecimal novoValor) {
//
//        // Aplica percentual
//        if (percentual != null) {
//            BigDecimal acrescimo = calcularPercentual(percentual);
//            this.valor = this.valor.add(acrescimo).setScale(2, BigDecimal.ROUND_HALF_UP);
//            return this.valor;
//        }
//
//        // Atualiza valor absoluto
//        if (novoValor != null) {
//            this.valor = novoValor.setScale(2, BigDecimal.ROUND_HALF_UP);
//            return this.valor;
//        }
//
//        // Caso não receba nada, mantém o valor atual
//        return this.valor != null ? this.valor : BigDecimal.ZERO.setScale(2);
//    }
}

