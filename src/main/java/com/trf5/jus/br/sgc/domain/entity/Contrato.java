package com.trf5.jus.br.sgc.domain.entity;

import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import com.trf5.jus.br.sgc.domain.enums.ModeloContrato;
import com.trf5.jus.br.sgc.domain.enums.StatusContrato;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrato", uniqueConstraints = @UniqueConstraint(name = "uk_contrato_numero_ano", columnNames = {"numeroContrato", "ano"}))
        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
public class Contrato extends BaseEntity{

    private Integer numeroContrato;
    private String ano;
    /** Processo Administrativo no SEI (ex: 0014946-73.2025.4.05.7000). */
    @Column(nullable = false, length = 30)
    private String numeroProcessoSei;

    @Column(columnDefinition = "TEXT")
    private String objetoDescricao;

    @Enumerated(EnumType.STRING)
    private ModeloContrato modeloContrato;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Unidade unidade;


    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal ValorInicial = BigDecimal.ZERO;

    /** Valor atual espelhando aditivos/apostilamento, com possibilidade de ajuste manual via campo "valorManualAtual"*/
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorAtual = BigDecimal.ZERO;

    /** se preeenchido, prevalece como "valor correto(pedido para contornar arrendondamentos/inconstancias" */
    private BigDecimal valorManualAtual;

    private LocalDate vigenciaInicio;
    private LocalDate vigenciaFim;

    @Column(nullable = false)
    private boolean prorrogavel;

    /** Prazo em meses(quando aplicavel)*/
    private Integer prorrogacaoMeses;

    /**Limte em meses (quando aplicavel)*/
    private Integer limiteMesesProrrogacao;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OrderBy("criadoEm" ASC)
    public List<Aditivo> aditivos = new ArrayList<>();

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    //   @OrderBy("criadoEm ASC")
    private List<Apostilamento> apostilamentos = new ArrayList<>();

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empenho> empenhos = new ArrayList<>();

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Garantia> garantias = new ArrayList<>();

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento>  documentos = new ArrayList<>();

    private String condPagamento;
    private LocalDate dataAssinatura;
    private LocalDate dataProposta;
    private LocalDate dataPublcacao;
    private String numeroDou;
    private String sessao;
    private String pagamentoDou;
    private String orgaoOrigem;
    private String ata;
    private String numeroPregao;
    private Boolean alertaEmail = Boolean.FALSE;
    private StatusContrato status;

    private String modalidadeFundamento;


    @OneToMany
    @JoinColumn(name = "gestor_id")
    private List<Gestor> gestores = new ArrayList<>();

    private Integer periodoBaseReajusteMeses;

    private Integer mesBaseCalculo;


    private String finalidade;

    @OneToMany(mappedBy = "contrato")
    private List<HistoricoFinanceiro> historicoFinanceiro = new ArrayList<>();


    @OneToMany(mappedBy = "contrato")
    private List<ItemContrato>  itemContratos = new ArrayList<>();



//    private LocalDate vigenciaAta;
//
//    @Column(columnDefinition = "TEXT")
//    private String processosVinculados;
//
//    private Integer maoDeObraResidente;
//
//    @ManyToOne
//    @JoinColumn(name = "usuario_criacao_id")
//    private Usuario usuarioCriacao;
//
//
//    @Column(columnDefinition = "TEXT")
//    private String fiscalizacao;
//
//
//    private String dadosPublicacao;
//
//
//    @OneToOne(mappedBy = "contrato")
//    private Encerramento encerramento;
//
//
//    @OneToMany(mappedBy = "contrato")
//    private List<HistoricoAlteracao> historicos;


    //@OneToOne(mappedBy = "contrato")
   // private Ata ata;

    /* ========================================================
       MÉTODOS DE NEGÓCIOS
    ======================================================== */

//    public long calcularDiasRestantes() {
//        if (vigenciaFim == null) return 0;
//        return LocalDate.now().until(vigenciaFim, ChronoUnit.DAYS);
//    }
//
//    public void atualizarStatus() {
//        if (vigenciaFim == null) {
//            status = StatusContrato.ATIVO;
//            return;
//        }
//
//        long dias = calcularDiasRestantes();
//
//        if (dias < 0) {
//            status = StatusContrato.EXPIRADO;
//        } else if (dias <= 30) {
//            status = StatusContrato.PENDENTE_RENOVACAO;
//        } else {
//            status = StatusContrato.ATIVO;
//        }
//    }
//
//    public boolean validarVigencia() {
//        if (vigenciaInicio == null || vigenciaFim == null) {
//            return false;
//        }
//        return !vigenciaInicio.isAfter(vigenciaFim);
//    }
}
