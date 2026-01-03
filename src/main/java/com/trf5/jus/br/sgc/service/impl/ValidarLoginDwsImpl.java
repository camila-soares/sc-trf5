package com.trf5.jus.br.sgc.service.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.trf5.jus.br.sgc.service.ValidarLoginDws;
import com.trf5.jus.br.sgc.util.SoapJacksonUtil;
import com.trf5.jus.br.sgc.util.Util;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Service
public class ValidarLoginDwsImpl implements ValidarLoginDws {


    @Override
    public String validar(Long paramIdSistema, String paramIdLogin, Long paramIdUsuario, String paramHashAgente) throws Exception {
        String retorno = null;

        String responseString = "";
        String outputString = "";

        String wsURL = Util.getPropProjeto().getString("WsSip");
        URL url = new URL(wsURL);

        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) connection;

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        String xmlInput = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sip=\"sipns\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + " <sip:validarLogin soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> "
                + " <IdLogin xsi:type=\"xsd:string\">" + paramIdLogin + "</IdLogin> "
                + " <IdSistema xsi:type=\"xsd:long\">" + paramIdSistema + "</IdSistema> "
                + " <IdUsuario xsi:type=\"xsd:long\">" + paramIdUsuario + "</IdUsuario> "
                + " <HashAgente xsi:type=\"xsd:string\">" + paramHashAgente + "</HashAgente> "
                + " </sip:validarLogin> "
                + "</soapenv:Body> "
                + "</soapenv:Envelope> ";


        byte[] buffer = new byte[xmlInput.length()];

        buffer = xmlInput.getBytes();

        bout.write(buffer);

        byte[] b = bout.toByteArray();

        String soapAction = "validarLogin";

        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("SOAPAction", soapAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();

        out.write(b);
        out.close();

        // Método antigo
		/*InputStreamReader isr = null;
		if (httpConn.getResponseCode() == 200) {
		  isr = new InputStreamReader(httpConn.getInputStream());
		} else {
		  isr = new InputStreamReader(httpConn.getErrorStream());
		}*/

        // Método atual
        InputStreamReader isr = null;
        if (httpConn.getResponseCode() == 200) {
            isr = new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8);
        } else {
            isr = new InputStreamReader(httpConn.getErrorStream(), StandardCharsets.UTF_8);
        }

        BufferedReader in = new BufferedReader(isr);

        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }

        JsonNode root = SoapJacksonUtil.parse(outputString);
        JsonNode response = SoapJacksonUtil.getBody(root);
        retorno = response.asText();

         SoapJacksonUtil.validateFault(root);
        return retorno;

    }
}