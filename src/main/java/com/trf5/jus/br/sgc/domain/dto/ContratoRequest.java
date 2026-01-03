package com.trf5.jus.br.sgc.domain.dto;

import com.trf5.jus.br.sgc.domain.enums.ModeloContrato;
import com.trf5.jus.br.sgc.domain.enums.StatusContrato;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoRequest {
    @NotBlank(message = "Número do contrato é obrigatório")
    private String numeroContrato;
    @NotNull(message = "numero do contrato é obrigatório")
    private String ano;
    @NotBlank(message = "número da ata é obrigatório")
    private String ata;

    private String numeroPregao;
    @NotBlank(message = "Número PA é obrigatório")
    private String numeroPA;
    private String numeroPADOrigem;

    private String ne;
//    @NotBlank(message = "modalidade é obrigatório")
//    private String modalidade;
    @NotBlank(message = "Objeto é obrigatório")
    private String objeto;

    @NotNull(message = "modelo de contrato é obrigatório")
    @Enumerated(EnumType.STRING)
    private ModeloContrato modeloContrato;

    private String modalidadeFundamento;

    private String processo;

    private String processosVinculados;
    @NotNull(message = "FornecedorId é obrigatório")
    private String fornecedorCnpj;
    @NotNull(message = "nome do fornecedor é obrigatório")
    private String fornecedorNome;
    @NotNull(message = "Valor inicial é obrigatório")
    @Positive(message = "Valor inicial deve ser positivo")
    private BigDecimal valorInicial;

    @NotNull(message = "Valor atual é obrigatório")
    @Positive(message = "Valor atual deve ser positivo")
    private BigDecimal valorAtualAnual;

    @NotNull(message = "Data assinatura é obrigatório")
    private LocalDate dataAssinatura;

    @NotNull(message = "Data Inicio vigencia é obrigatório")
    private LocalDate dataInicioVigencia;

    @NotNull(message = "Data Fim vigencia é obrigatório")
    private LocalDate dataFimVigencia;
    private LocalDate descricao;

    private Boolean prorrogavel;
    @NotNull(message = "Prazo é obrigatório")
    private Integer prazo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status é obrigatório")
    private StatusContrato status;

    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;
    @NotBlank(message = "Unidade técnica é obrigatório")
    private String unidade;
    private String observacoes;

    private Long usuarioCriacaoId;

    private String fiscalizacao;

    private String dadosPublicacao;
}

