package com.trf5.jus.br.sgc.service;


import com.trf5.jus.br.sgc.domain.dto.FornecedorDTO;
import com.trf5.jus.br.sgc.domain.entity.Fornecedor;

public interface FornecedoresServices {

    FornecedorDTO addFornecedor(Fornecedor fornecedor);
    FornecedorDTO getFornecedorById(String id);
    FornecedorDTO getFornecedorByName(String name);
    FornecedorDTO getAllFornecedores();
}
