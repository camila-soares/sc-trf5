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

import com.trf5.jus.br.sgc.util.Util;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class EncryptDws {

    public String traduzir(String paramPwd) throws Exception {
        String retorno = "50";
        String responseString = "";
        String outputString ="";

        String wsURL = Util.getPropProjeto().getString("WsTranslate");
        URL url = new URL(wsURL);

        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        String xmlInput = " <Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"> "
                + " <Body> "
                + "	<getTranslate xmlns=\"urn:geTranslate\">	"
                + " <senhaDecr>"+paramPwd.replaceAll("&", "&amp;")+"</senhaDecr> "
                + " </getTranslate> "
                + " </Body> "
                + " </Envelope> ";

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();

        String soapAction = "getTranslate";

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
            isr = new InputStreamReader(httpConn.getInputStream());
        } else {
            isr = new InputStreamReader(httpConn.getErrorStream());
        }

        BufferedReader in = new BufferedReader(isr);

        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }

        int posI = 0;
        int posF = 1;
        posI = outputString.indexOf("<return xsi:type=\"xsd:string\">")+"<return xsi:type=\"xsd:string\">".length();
        posF = outputString.indexOf("</return>");
        retorno = outputString.substring(posI, posF);

        return retorno;
    }


}