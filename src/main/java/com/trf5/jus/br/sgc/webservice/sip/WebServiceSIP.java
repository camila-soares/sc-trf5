package com.trf5.jus.br.sgc.webservice.sip;

import java.util.ResourceBundle;

public class WebServiceSIP {

	private static ResourceBundle prop = ResourceBundle.getBundle("br.jus.trf5.webService");
	
	public static String autenticarSIP(String login, String senha, String orgao) throws Exception{

		String retorno = null;
		EncryptCliente encryptCliente = new EncryptCliente();
		AutenticarCompletoCliente autenticarCompletoCliente = new AutenticarCompletoCliente();
		ValidarLoginCliente validarLoginCliente = new ValidarLoginCliente();
		
		String url = prop.getString("link_TranslateWS");
		String urlSIP = prop.getString("link_SIPWS");
		
		try {			
			String secaoString = "TRF5";
			
			// Autentica��o via SIP			
			String paramSenha =  encryptCliente.traduzir(senha, url);
			RetornoAutenticarCompleto retornoAutCompleto = autenticarCompletoCliente.autenticar(orgao, login, paramSenha, "SGC", secaoString, urlSIP);
			retorno = validarLoginCliente.validar(retornoAutCompleto.getIdSistema(), retornoAutCompleto.getIdLogin(), retornoAutCompleto.getIdUsuario(), retornoAutCompleto.getHashAgente(), urlSIP);
			// Fim via SIP
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		/*catch (Exception e) {
			throw new Exception("Falha na conex�o com o Web Service do SIP.");
		} */
		
		return retorno;
	}
}
