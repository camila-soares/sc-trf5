package com.trf5.jus.br.sgc.domain.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;


@Data
public class LoginPNCDTO {

    private String login;
	private String senha;

    public String toJson()  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public LoginPNCDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

}
