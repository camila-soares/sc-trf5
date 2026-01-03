package com.trf5.jus.br.sgc.service;

import com.trf5.jus.br.sgc.domain.dto.login.RetornoAutenticarCompleto;

public interface AutenticarCompletoDws {

    public RetornoAutenticarCompleto autenticar(String paramOrgao, String paramUser, String paramPwd) throws Exception;
}
