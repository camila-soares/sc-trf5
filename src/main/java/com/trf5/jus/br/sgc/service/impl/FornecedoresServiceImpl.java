package com.trf5.jus.br.sgc.service.impl;


import com.trf5.jus.br.sgc.domain.dto.FornecedorDTO;
import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
import com.trf5.jus.br.sgc.repository.FornecedoresRepository;
import com.trf5.jus.br.sgc.service.FornecedoresServices;
import org.springframework.stereotype.Service;

@Service
public class FornecedoresServiceImpl implements FornecedoresServices {

    private final FornecedoresRepository  fornecedoresRepository;

    public FornecedoresServiceImpl(FornecedoresRepository fornecedoresRepository) {
        this.fornecedoresRepository = fornecedoresRepository;
    }

    @Override
    public FornecedorDTO addFornecedor(Fornecedor fornecedor) {
        return null;
    }

    @Override
    public FornecedorDTO getFornecedorById(String id) {
        return null;
    }

    @Override
    public FornecedorDTO getFornecedorByName(String name) {
        return null;
    }

    @Override
    public FornecedorDTO getAllFornecedores() {
        return null;
    }
}
