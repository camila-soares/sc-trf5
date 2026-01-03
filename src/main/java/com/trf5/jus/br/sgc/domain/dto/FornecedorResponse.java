package com.trf5.jus.br.sgc.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorResponse {
    private String nome;
    private String fantasia;
    private String cpfCnpj;
    private String status;
    private String capital_social;
}
