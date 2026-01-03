package com.trf5.jus.br.sgc.webservice.impl;



import com.trf5.jus.br.sgc.domain.dto.LoginPNCDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.trf5.jus.br.sgc.webservice.WebServiceNPCP;

import com.trf5.jus.br.sgc.util.Util;
@Log4j2
public class WebServicePNCPImpl implements WebServiceNPCP {

    String baseUrlPNCP = Util.getPropProjeto().getString("wsPNCP");
    private static final String LINK_ORGAO = "/v1/orgaos/";
    private static final String APLICATION_JSON = "application/json";

    @Override
    public ResponseEntity<String> login() {
       try {
        String url = "v1/usuarios/login";
        String loginJson = new LoginPNCDTO(Util.getPropProjeto().getString("loginPNCP"),
                Util.getPropProjeto().getString("senhaPNCP")).toJson();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = baseUrlPNCP + url;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", APLICATION_JSON);
        ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, loginJson, String.class);
        return response;
        } catch (Exception e) {
            e.printStackTrace();
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    }

    @Override
    public ResponseEntity<String> cadastrarUnidadesPNCP(String unidadeJson, String cnpj,ResponseEntity<String> retornoLogin) {
        try {
            String url = LINK_ORGAO + cnpj+ "/unidades";
            RestTemplate restTemplate = new RestTemplate();
            String resourceUrl = baseUrlPNCP + url;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", APLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION,  retornoLogin.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
            HttpEntity<String> request = new HttpEntity<>(unidadeJson, headers);
            return restTemplate.exchange(resourceUrl, HttpMethod.POST, request,String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Unimplemented method 'cadastrarUnidadesPNCP'");
        }
    }
}
      
    

 
