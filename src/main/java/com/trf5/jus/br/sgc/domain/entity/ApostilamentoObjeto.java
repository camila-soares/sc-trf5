package com.trf5.jus.br.sgc.domain.entity;

import com.trf5.jus.br.sgc.domain.enums.TipoApostilamentoObjeto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "apostilamento_objeto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApostilamentoObjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ==============================
       RELACIONAMENTOS
    ============================== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apostilamento_id")
    private Apostilamento apostilamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objeto_contrato_id")
    private ObjetoContrato objetoContrato;

    /* ==============================
       CAMPOS
    ============================== */

    private LocalDate dataBaseProposta;

    private BigDecimal valorReajuste;           // valor reajustado ou recomposto
    private BigDecimal percentual;              // percentual aplicado
    private BigDecimal valorTotalAtualizado;    // valor final após reajuste

    @Enumerated(EnumType.STRING)
    private TipoApostilamentoObjeto tipo;

    private LocalDateTime dataCriacao;

    /* ==============================
       AUDITORIA
    ============================== */
    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
    }

    /* ==============================
       REGRAS DE NEGÓCIO
    ============================== */

    /**
     * Calcula o valor do reajuste usando o percentual informado.
     *
     * Exemplo: valor atual = 100.000, percentual = 5%
     * → reajuste = 5.000
     */
    public BigDecimal calcularReajuste() {
        if (percentual == null || objetoContrato == null)
            return BigDecimal.ZERO;

        BigDecimal base = objetoContrato.getValor(); // valor do objeto
        BigDecimal reajuste = base.multiply(percentual)
                .divide(BigDecimal.valueOf(100));

        this.valorReajuste = reajuste;
        return reajuste;
    }

    /**
     * Aplica o percentual sobre o valor do objeto e retorna o novo valor.
     */
    public BigDecimal aplicarPercentual() {
        if (objetoContrato == null) return null;

        BigDecimal base = objetoContrato.getValor();

        BigDecimal novoValor = (percentual == null)
                ? base
                : base.add(calcularReajuste());

        this.valorTotalAtualizado = novoValor;
        return novoValor;
    }
}
