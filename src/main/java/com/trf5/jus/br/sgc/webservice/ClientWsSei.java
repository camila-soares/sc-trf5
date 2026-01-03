//package com.trf5.jus.br.sgc.webservice;
//
//import com.trf5.jus.br.sgc.domain.dto.sei.*;
//import com.trf5.jus.br.sgc.util.AuthUtils;
//import com.trf5.jus.br.sgc.util.Util;
//import com.trf5.jus.br.sgc.webservice.wsbr.WebServiceBR;
//import net.sf.jasperreports.engine.util.JRStyledText;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.StandardCharsets;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ClientWsSei {
//
//    public String chaveAcesso;
//    private String endPoint = Util.getPropProjeto().getString("WsSei");
//    private String siglaSistema = Util.getPropProjeto().getString("siglaSistemaSei");
//    private String siglaSei = Util.getPropProjeto().getString("idSiglaSei");
//    private String[] tarefasModulos = { "1", "2", "5", "6", "7", "12", "13", "18", "19", "20", "21", "24", "26", "27",
//            "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45",
//            "47", "48", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65",
//            "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83",
//            "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100",
//            "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115",
//            "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "134",
//            "135", "136" };
//
//
//    public ClientWsSei() {
//        this.chaveAcesso = WebServiceBR.consultarChaveAcesso(siglaSei, siglaSistema);
//    }
//
//    public List<TipoProcedimento> listarTipoProcedimentto() throws MalformedURLException, RemoteException {
//        SeiBindingStub stub;
//        try {
//            stub = new SeiBindingStub(new URL(endPoint), null);
//
//            TipoProcedimento[] array = stub.listarTiposProcedimento(siglaSistema, chaveAcesso, null, null, null);
//
//            if (array != null){
//                return Arrays.asList(array);
//            }
//        }catch (Exception e ){
//            throw new RuntimeException(e.getMessage());
//        }
//        return null;
//    }
//
//    public List<Usuario> listarUsuario() throws MalformedURLException {
//        SeiBindingStub stub;
//        try {
//            stub = new SeiBindingStub(new URL(endPoint), null);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//        return null;
//    }
//
//    private List<Unidade> consultarUnidadePorUsuario(String idUsuarioSip) throws   Exception {
//        List<Unidade> retorno = new ArrayList<>();
//
//        String secao = Util.getSecao(AuthUtils.getOrgao(), "CODIGO");
//
//        String response = "";
//        String outptResponse = "";
//
//        String codigoSistema = Util.getPropProjeto().getString("idSistemaNoSip");
//        String chaveAcesso = "";
//
//        String xmlInput = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
//                + "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sip=\"sipns\">"
//                + "<soapenv:Header/>" + "<soapenv:Body>"
//                + " <sip:carregarUnidades soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> "
//                + "<ChaveAcesso xsi:type=\"xsd:string\">" + chaveAcesso + "</ChaveAcesso> "
//                + "<IdSistema xsi:type=\"xsd:long\">" + codigoSistema + "</IdSistema> "
//                + "<IdUsuario xsi:type=\"xsd:long\">" + idUsuarioSip + "</IdUsuario>"
//                + "<IdUnidade xsi:type=\"xsd:long\">0</IdUnidade>" + "</sip:carregarUnidades> " + "</soapenv:Body> "
//                + "</soapenv:Envelope> ";
//
//        byte[] buffer = new byte[xmlInput.length()];
//        buffer = xmlInput.getBytes();
//
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        bout.write(buffer);
//
//        byte[] b = bout.toByteArray();
//
//        String soapAction = "carregarUnidades";
//
//        String wsURL = Util.getPropProjeto().getString("WsSip");
//        URL url = new URL(wsURL);
//
//        URLConnection connection = url.openConnection();
//        HttpURLConnection httpConn = (HttpURLConnection) connection;
//        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
//        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//        httpConn.setRequestProperty("SOAPAction", soapAction);
//        httpConn.setRequestMethod("POST");
//        httpConn.setDoOutput(true);
//        httpConn.setDoInput(true);
//
//        OutputStream out = httpConn.getOutputStream();
//        out.write(b);
//
//
//        InputStreamReader isr = null;
//        if (httpConn.getResponseCode() == 200) {
//            isr = new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8);
//        } else {
//            isr = new InputStreamReader(httpConn.getErrorStream(), StandardCharsets.UTF_8);
//        }
//
//        BufferedReader in = new BufferedReader(isr);
//
//        while ((response = in.readLine()) != null) {
//            outptResponse = outptResponse + response;
//        }
//
//        Document doc = Util.loadXMLFromString(response);
//        doc.getDocumentElement().normalize();
//
//        NodeList nValue = doc.getElementsByTagName("value");
//
//        // setting Usuario SIP
//        int sizeList = nValue.getLength();
//        for (int temp = 0; temp < sizeList; temp++) {
//            Unidade rcu = new Unidade();
//            boolean isSecao = true;
//
//            Node nNodeValue = nValue.item(temp);
//            if (nNodeValue.getNodeType() == Node.ELEMENT_NODE) {
//                rcu.setIdUnidade(nNodeValue.getChildNodes().item(0).getTextContent());
////				rcu.setMatricula(nNodeValue.getChildNodes().item(1).getTextContent());
//                rcu.setSiglaUnidade(nNodeValue.getChildNodes().item(2).getTextContent());
//                rcu.setNomeUnidade(nNodeValue.getChildNodes().item(3).getTextContent());
//              //  rcu.setSinProtocolo(nNodeValue.getChildNodes().item(4).getTextContent());
////				rcu.setAtivo(nNodeValue.getChildNodes().item(5).getTextContent());
//
//            }
//
//            if (isSecao) {
//                retorno.add(rcu);
//            }
//        }
//
//        return retorno.stream().sorted(Comparator.comparing(Unidade::getSiglaUnidade)).collect(Collectors.toList());
//    }
//
//    public List<Andamento> listarAndamentos(ProcessoSei processo) throws MalformedURLException, RemoteException {
//        SeiBindingStub stub;
//        try {
//            stub = new SeiBindingStub(new java.net.URL(endPoint), null);
//
//            Andamento[] array = stub.listarAndamentos(siglaSistema, chaveAcesso, processo.getIdUnidadeSei(),
//                    processo.getNumeroProcesso(), "S", null, tarefasModulos, null);
//
//            if (array != null) {
//                return Arrays.asList(array);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//        return null;
//    }
//
//
//
//}
//
