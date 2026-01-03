package com.trf5.jus.br.sgc.service.impl;
import com.trf5.jus.br.sgc.domain.dto.sei.RetornoConsultarUsuario;
import com.trf5.jus.br.sgc.util.Util;
import com.trf5.jus.br.sgc.webservice.ConsultaUsuarioDws;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Document;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class ConsultarUsuarioDwImp implements ConsultaUsuarioDws {


    @Override
    public List<RetornoConsultarUsuario> consultarUsuarios(String orgao) throws Exception {
        List<RetornoConsultarUsuario> retorno = new ArrayList<RetornoConsultarUsuario>();

        String secao = Util.getSecao(orgao, "CODIGO");

        String responseString = "";
        String outputString = "";

        String codigoSistema = Util.getPropProjeto().getString("idSistemaNoSip");
        String chaveAcesso = "295a1305214492553fae5e2eef4ba884a96e0fc39111b866ee7b08f689aa02e470b2702d";


//                WebServiceBR.consultarChaveAcesso(Util.getPropProjeto().getString("siglaSIP"),
//                Util.getPropProjeto().getString("siglaSistemaSei"));

        String xmlInput = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sip=\"sipns\">"
                + "<soapenv:Header/>" + "<soapenv:Body>"
                + " <sip:carregarUsuarios soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> "
                + "<ChaveAcesso xsi:type=\"xsd:string\">" + chaveAcesso + "</ChaveAcesso> "
                + "<IdSistema xsi:type=\"xsd:long\">" + codigoSistema + "</IdSistema> " + "</sip:carregarUsuarios> "
                + "</soapenv:Body> " + "</soapenv:Envelope> ";

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(buffer);

        byte[] b = bout.toByteArray();

        String soapAction = "carregarUsuarios";

        String wsURL = Util.getPropProjeto().getString("WsSip");
        URL url = new URL(wsURL);

        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) connection;
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

        Document doc = Util.loadXMLFromString(outputString);
        doc.getDocumentElement().normalize();

        NodeList nValue = doc.getElementsByTagName("value");

        // setting Usuario SIP
        int sizeList = nValue.getLength();
        for (int temp = 0; temp < sizeList; temp++) {
            RetornoConsultarUsuario rcu = new RetornoConsultarUsuario();
            boolean isSecao = true;

            Node nNodeValue = nValue.item(temp);
            if (nNodeValue.getNodeType() == Node.ELEMENT_NODE) {
                if (nNodeValue.getChildNodes().item(0) != null && nNodeValue.getChildNodes().item(0).getLastChild() != null)
                    rcu.setId(nNodeValue.getChildNodes().item(0).getLastChild().getTextContent());
                if (nNodeValue.getChildNodes().item(1) != null)
                    rcu.setMatricula(nNodeValue.getChildNodes().item(1).getLastChild().getTextContent());
                if (nNodeValue.getChildNodes().item(2) != null)
                    rcu.setIdorgao(nNodeValue.getChildNodes().item(2).getLastChild().getTextContent());
                if (nNodeValue.getChildNodes().item(3) != null)
                    rcu.setLogin(nNodeValue.getChildNodes().item(3).getLastChild().getTextContent());
                if (nNodeValue.getChildNodes().item(4) != null)
                    rcu.setNome(nNodeValue.getChildNodes().item(4).getLastChild().getTextContent());
                if (nNodeValue.getChildNodes().item(8) != null)
                    rcu.setAtivo(nNodeValue.getChildNodes().item(8).getLastChild().getTextContent());

                if (!Util.isNullOrEmpty(rcu.getIdorgao())) {
                    if (!rcu.getIdorgao().equalsIgnoreCase(secao)) {
                        isSecao = false;
                    }
                } else {
                    isSecao = false;
                }
            }
            if (isSecao) {
                retorno.add(rcu);
            }
        }
        return retorno.stream().sorted(Comparator.comparing(RetornoConsultarUsuario::getNome))
                .collect(Collectors.toList());
    }
}
