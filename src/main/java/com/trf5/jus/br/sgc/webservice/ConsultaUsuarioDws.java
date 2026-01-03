package com.trf5.jus.br.sgc.webservice;

import com.trf5.jus.br.sgc.domain.dto.sei.RetornoConsultarUsuario;

import java.util.List;

public interface ConsultaUsuarioDws {

    public List<RetornoConsultarUsuario> consultarUsuarios(String orgao) throws Exception;
}
