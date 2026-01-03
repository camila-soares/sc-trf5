package com.trf5.jus.br.sgc.webservice.sip;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class AutenticarCompletoCliente {

	@SuppressWarnings("unused")
	public RetornoAutenticarCompleto autenticar(String paramOrgao, String paramUser,String paramPwd, String paramSistema, String paramSiglaOrgao, String linkWS) throws Exception{
		
		RetornoAutenticarCompleto retorno = new RetornoAutenticarCompleto();
		retorno.setValidado(false);
		
		
		String responseString = "";
		String outputString ="";
		
		String wsURL = linkWS;
		URL url = new URL(wsURL);
		
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection)connection;
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
				
		String xmlInput = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sip=\"sipns\">"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+" <sip:autenticarCompleto soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> "
				+ "<IdOrgao xsi:type=\"xsd:string\">"+paramOrgao+"</IdOrgao> "
						+ "<Sigla xsi:type=\"xsd:string\">"+paramUser+"</Sigla> "
						+ "<Senha xsi:type=\"xsd:string\">"+paramPwd+"</Senha> "
						+ "<SiglaSistema xsi:type=\"xsd:string\">"+paramSistema+"</SiglaSistema> "
						+ "<SiglaOrgaoSistema xsi:type=\"xsd:string\">"+paramSiglaOrgao+"</SiglaOrgaoSistema>"
				+"</sip:autenticarCompleto> "
				+ "</soapenv:Body> "
				+ "</soapenv:Envelope> ";
		
		
		byte[] buffer = new byte[xmlInput.length()];
		
		buffer = xmlInput.getBytes();
		
		bout.write(buffer);
		
		byte[] b = bout.toByteArray();
		
		String soapAction = "autenticarCompleto";
		
		httpConn.setRequestProperty("Content-Length",
				String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", soapAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();

		out.write(b);
		out.close();
		
		InputStreamReader isr = null;
		if (httpConn.getResponseCode() == 200) {
		  isr = new InputStreamReader(httpConn.getInputStream());
		} else {
		  isr = new InputStreamReader(httpConn.getErrorStream());
		}
		
		BufferedReader in = new BufferedReader(isr);
		
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}
		
		System.out.println(outputString);
	
		Document doc = loadXMLFromString(outputString);
		
		doc.getDocumentElement().normalize();
	
		NodeList nListHash = doc.getElementsByTagName("HashAgente");
		
		System.out.println("----------------------------");
		//setting hash agente
		Integer sizeList = nListHash.getLength();
		for(int temp = 0; temp < sizeList;temp++){
			Node nNode = nListHash.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				retorno.setHashAgente(element.getTextContent());
				System.out.println("Resultado --> "+element.getTextContent());
			}
		}
						
		//setting id do sistema		
		NodeList nListIdSist = doc.getElementsByTagName("IdSistema");
				
		System.out.println("----------------------------");
				
		Integer sizeListIdSistema = nListIdSist.getLength();
				
		for(int temp = 0; temp < sizeList;temp++){
			Node nNode = nListIdSist.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				retorno.setIdSistema(Long.parseLong(element.getTextContent()));
				System.out.println("Resultado --> "+element.getTextContent());
			}
		}

		//setting Id do usuario
				
		NodeList nListIdUsuario = doc.getElementsByTagName("IdUsuario");
		
		System.out.println("----------------------------");
				
		Integer sizeListIdUsuario = nListIdUsuario.getLength();
				
		for(int temp = 0; temp < sizeList;temp++){
			Node nNode = nListIdUsuario.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				retorno.setIdUsuario(Long.parseLong(element.getTextContent()));
				System.out.println("Resultado --> "+element.getTextContent());
			}
		}
		//setting Id do login
				
		NodeList nListIdLogin = doc.getElementsByTagName("IdLogin");
		
		System.out.println("----------------------------");
				
		Integer sizeListIdLogin = nListIdLogin.getLength();
				
		for(int temp = 0; temp < sizeList;temp++){
			Node nNode = nListIdLogin.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				retorno.setIdLogin(element.getTextContent());
				System.out.println("Resultado --> "+element.getTextContent());
			}
		}
						
		//setting Chave de Exibi��o 
		if(sizeList<1){
			retorno.setValidado(false);
		}else{
			retorno.setValidado(true);	
		}
										
		return retorno;
	}
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
}
