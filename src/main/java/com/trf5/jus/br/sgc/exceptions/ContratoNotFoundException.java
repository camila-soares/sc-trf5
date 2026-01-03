package com.trf5.jus.br.sgc.exceptions;

public class ContratoNotFoundException extends  RuntimeException {
    public ContratoNotFoundException(Long id) {
        super("Contrato não encontrado: " + id);
    }

    public ContratoNotFoundException() {

    }
}
