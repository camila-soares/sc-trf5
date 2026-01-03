package com.trf5.jus.br.sgc.domain.entity;

import com.trf5.jus.br.sgc.domain.enums.TipoAditivo;
import com.trf5.jus.br.sgc.domain.enums.UnidadePrazo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "aditivo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aditivo extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contrato contrato;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false, length = 30)
    private String seiNumeroProcesso;

    @Column(nullable = false, length = 1000)
    private String descricaoObjeto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length =  30)
    private TipoAditivo tipo;

    /**Usar quando for prorrogação. */
    private Integer prorragacaoMeses;

    /** pode ser nulo quando "sem valor"*/
    @Column(precision = 19, scale = 2)
    private BigDecimal valor;

    /** Se preenchido, força o novo valor atual do contrato (pedido de ajuste manual). */
    @Column(precision = 19, scale = 2)
    private BigDecimal valorContratoResultadoManual;

    private LocalDate dataAssinatura;

    private BigDecimal novoValor;

    private BigDecimal percentual;

    private LocalDate vigenciaInicio;

    private LocalDate novoPrazoFim;

    private UnidadePrazo unidadePrazo;


    /* =====================================================
       AUDITORIA AUTOMÁTICA
    ====================================================== */


   /* ===============================
         MÉTODOS DE NEGÓCIO
     =============================== */

    /** ACRÉSCIMO: soma valor ao valorAtual */
    public void aplicarAcrescimo() {
        if (novoValor == null) return;
        contrato.setValorAtual(contrato.getValorAtual().add(novoValor));
    }

    /** SUPRESSÃO: subtrai valor ao valorAtual */
    public void aplicarSupressao() {
        if (novoValor == null) return;
        contrato.setValorAtual(contrato.getValorAtual().subtract(novoValor));
    }

    /** REAJUSTE: aplica percentual ex: 5% */
    public void aplicarReajuste() {
        if (percentual == null) return;
        BigDecimal reajuste = contrato.getValorAtual()
                .multiply(percentual)
                .divide(BigDecimal.valueOf(100));

        contrato.setValorAtual(contrato.getValorAtual().add(reajuste));
        this.valor = reajuste;
    }

    /** PRORROGAÇÃO: adiciona meses/anos à vigência
    public void prorrogarPrazo() {
        if (prorragacaoMeses == null || unidadePrazo == null) return;

        LocalDate novaData = switch (unidadePrazo) {
            case DIAS -> contrato.getVigenciaInicio().plusDays(prorragacaoMeses);
            case MESES -> contrato.getVigenciaFim().plusMonths(prorragacaoMeses);
            case ANOS -> contrato.getVigenciaFim().plusYears(prorragacaoMeses);
        };

        contrato.setVigenciaFim(novaData);
        this.novaVigencia = novaData;
    }*/

    /** Valida percentual (não pode passar de 25% supressão/acréscimo — regra TCU) */
    public void validarPercentual() {
        if (percentual == null) return;

        if (percentual.compareTo(BigDecimal.valueOf(25)) > 0)
            throw new IllegalArgumentException("Percentual excede o limite legal de 25%.");
    }
}

