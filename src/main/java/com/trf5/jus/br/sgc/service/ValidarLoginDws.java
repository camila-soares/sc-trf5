package com.trf5.jus.br.sgc.service;

public interface ValidarLoginDws {

    public String validar(Long paramIdSistema, String paramIdLogin,Long paramIdUsuario, String paramHashAgente) throws Exception;
}
