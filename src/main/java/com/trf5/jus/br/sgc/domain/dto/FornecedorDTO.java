package com.trf5.jus.br.sgc.domain.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorDTO {
    private Long id;
    private String cpfCnpj;
    private String nome;
    private String ramoAtividade;

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    private String email;
    private String telefone1;
    private String telefone2;
}
