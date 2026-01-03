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

public class ValidarLoginCliente {

	public String validar(Long paramIdSistema, String paramIdLogin,Long paramIdUsuario, String paramHashAgente, String linkWS) throws Exception{
		String retorno = null;
		
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
				+" <sip:validarLogin soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> "
				+ " <IdLogin xsi:type=\"xsd:string\">"+paramIdLogin+"</IdLogin> "
				+ " <IdSistema xsi:type=\"xsd:long\">"+paramIdSistema+"</IdSistema> "
					+ " <IdUsuario xsi:type=\"xsd:long\">"+paramIdUsuario+"</IdUsuario> "
					+ " <HashAgente xsi:type=\"xsd:string\">"+paramHashAgente+"</HashAgente> "
				+ " </sip:validarLogin> "
				+ "</soapenv:Body> "
				+ "</soapenv:Envelope> ";
		
		
		byte[] buffer = new byte[xmlInput.length()];
		
		buffer = xmlInput.getBytes();
		
		bout.write(buffer);
		
		byte[] b = bout.toByteArray();
		
		String soapAction = "validarLogin";
		
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
				
		Document doc = loadXMLFromString(outputString);
		doc.getDocumentElement().normalize();
		
		NodeList nListHash = doc.getElementsByTagName("strIdOrigemUsuario");
		System.out.println("----------------------------");
		//setting hash agente
		Integer sizeList = nListHash.getLength();
		for(int temp = 0; temp < sizeList;temp++){
			Node nNode = nListHash.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				System.out.println("Resultado --> "+element.getTextContent());
				retorno = element.getTextContent();
			}
		}
		
		nListHash = doc.getElementsByTagName("arrPermissoes");
		System.out.println("----------------------------");
		//setting arrPermissoes
		sizeList = nListHash.getLength();
		for(int temp = 0; temp < sizeList;temp++){
			Node nNode = nListHash.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				System.out.println("Resultado --> "+element.getTextContent());
				if(!element.getTextContent().contains("flag_perfil_basico")){
					throw new Exception("Usu�rio n�o possui permiss�o de acesso.");
				}
			}
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
