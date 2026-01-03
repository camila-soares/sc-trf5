package com.trf5.jus.br.sgc.webservice;

import org.springframework.http.ResponseEntity;

public interface WebServiceNPCP {

    public ResponseEntity<String> login();
    public ResponseEntity<String> cadastrarUnidadesPNCP(String unidadeJson, String cnpj, ResponseEntity<String> retornoLogin);
}
