//package com.trf5.jus.br.sgc.service.impl;
//
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//@Service
//public class SipClient {
//
//    public final String SOAP_URL = "https://sip5h.trf5.jus.br/sip/ws/SipWS.php";
//
//    public String autenticar(
//            String chaveAcesso,
//            String idOrgao,
//            String idContexto,
//            String sigla,
//            String senha
//    ) throws Exception {
//
//        String xml = montarEnvelope(chaveAcesso, idOrgao, idContexto, sigla, senha);
//
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(SOAP_URL))
//                .header("Content-Type", "text/xml; charset=utf-8")
//                .header("SOAPAction", "sipnsAction")   // alguns servidores exigem sipns#autenticar
//                .POST(HttpRequest.BodyPublishers.ofString(xml))
//                .build();
//
//        HttpResponse<String> response =
//                client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        return response.body();
//    }
//
//    private static String montarEnvelope(
//            String chaveAcesso,
//            String idOrgao,
//            String idContexto,
//            String sigla,
//            String senha
//    ) {
//
//        return """
//                <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//                               xmlns:xsd="http://www.w3.org/2001/XMLSchema"
//                               xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
//                               xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
//                    <soap:Body soap:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
//                        <autenticar xmlns="sipns">
//                            <ChaveAcesso xsi:type="xsd:string">%s</ChaveAcesso>
//                            <IdOrgao xsi:type="xsd:string">%s</IdOrgao>
//                            <IdContexto xsi:type="xsd:string">%s</IdContexto>
//                            <Sigla xsi:type="xsd:string">%s</Sigla>
//                            <Senha xsi:type="xsd:string">%s</Senha>
//                        </autenticar>
//                    </soap:Body>
//                </soap:Envelope>
//                """.formatted(chaveAcesso, idOrgao, idContexto, sigla, senha);
//    }
//}
