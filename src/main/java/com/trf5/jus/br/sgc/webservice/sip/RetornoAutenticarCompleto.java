package com.trf5.jus.br.sgc.webservice.sip;

public class RetornoAutenticarCompleto {

	private Long idSistema;
	private Long idUsuario;
	private String idLogin;
	private String hashAgente;
	private boolean validado;
	
	public Long getIdSistema() {
		return idSistema;
	}
	
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getIdLogin() {
		return idLogin;
	}
	
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	
	public String getHashAgente() {
		return hashAgente;
	}
	
	public void setHashAgente(String hashAgente) {
		this.hashAgente = hashAgente;
	}
	
	public boolean isValidado() {
		return validado;
	}
	
	public void setValidado(boolean validado) {
		this.validado = validado;
	}	
}
