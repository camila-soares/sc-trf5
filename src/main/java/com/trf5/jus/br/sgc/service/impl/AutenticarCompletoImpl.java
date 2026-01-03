package com.trf5.jus.br.sgc.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.trf5.jus.br.sgc.domain.dto.login.RetornoAutenticarCompleto;
import com.trf5.jus.br.sgc.service.AutenticarCompletoDws;
import com.trf5.jus.br.sgc.util.SoapJacksonUtil;
import com.trf5.jus.br.sgc.util.Util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


public class AutenticarCompletoImpl implements AutenticarCompletoDws {


    @Override
    public RetornoAutenticarCompleto autenticar(String paramOrgao, String paramUser, String paramPwd) throws Exception {

        RetornoAutenticarCompleto retorno = new RetornoAutenticarCompleto();
        retorno.setValidado(false);

        String responseString = "";
        String outputString ="";

        String wsURL = Util.getPropProjeto().getString("WsSip");
        URL url = new URL(wsURL);

        String secao = Util.getSecao(paramOrgao, "CODIGO");
        String sistema = Util.getPropProjeto().getString("siglaSistemaSei");

//        String chaveAcesso= WebServiceBR.consultarChaveAcesso(Util.getPropProjeto().getString("siglaSIP"), Util.getPropProjeto().getString("siglaSistemaSei"));

        String chaveAcesso = "295a1305214492553fae5e2eef4ba884a96e0fc39111b866ee7b08f689aa02e470b2702d";
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        String xmlInput = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sip=\"sipns\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                +" <sip:autenticarCompleto soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> "
                + "<ChaveAcesso xsi:type=\"xsd:string\">"+chaveAcesso+"</ChaveAcesso> "
                + "<IdOrgao xsi:type=\"xsd:string\">"+secao+"</IdOrgao> "
                + "<Sigla xsi:type=\"xsd:string\">"+paramUser+"</Sigla> "
                + "<Senha xsi:type=\"xsd:string\">"+paramPwd+"</Senha> "
                + "<SiglaSistema xsi:type=\"xsd:string\">"+sistema+"</SiglaSistema> "
                + "<SiglaOrgaoSistema xsi:type=\"xsd:string\">TRF5</SiglaOrgaoSistema>"
                +"</sip:autenticarCompleto> "
                   + "</soapenv:Body> "
                + "</soapenv:Envelope> ";


        byte[] buffer = new byte[xmlInput.length()];

        buffer = xmlInput.getBytes();

        bout.write(buffer);

        byte[] b = bout.toByteArray();

        String soapAction = "autenticarCompleto";

        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
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

        retorno.setIdSistema(response.path("IdSistema").path("").asLong());

        retorno.setIdUsuario(response.path("IdUsuario").path("").asLong());

        retorno.setIdLogin(response.path("IdLogin").path("").asText());

        retorno.setHashAgente(response.path("HashAgente").path("").asText());

        retorno.setValidado(true);

        return retorno;
    }


}
