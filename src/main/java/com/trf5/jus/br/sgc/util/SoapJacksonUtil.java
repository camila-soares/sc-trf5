package com.trf5.jus.br.sgc.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public final class SoapJacksonUtil {

    private static final XmlMapper xmlMapper = new XmlMapper();

    private SoapJacksonUtil() {
    }

    /** Converte SOAP XML em JsonNode */
    public static JsonNode parse(String soapXml) throws Exception {
        return xmlMapper.readTree(soapXml.getBytes());
    }

    /** Retorna o nó Body do SOAP */
    public static JsonNode getBody(JsonNode root) {
        return root
                .path("Body")
                .path("autenticarCompletoResponse")
                .path("returnAutenticarCompleto");
    }

    public static JsonNode getBodyUsuarios(JsonNode root) {
        return root.path("Body")
                .path("carregarUsuariosResponse")
                .path("returnUsuarios");
    }
    /** Lança exceção caso exista SOAP Fault */
    public static void validateFault(JsonNode body) {
        JsonNode fault = body.path("Fault");

        if (!fault.isMissingNode()) {
            String mensagem = fault.path("faultstring").asText("Erro desconhecido SOAP");
            throw new RuntimeException(mensagem);
        }
    }

    /** Retorna o nó da resposta */
    public static JsonNode getResponse(JsonNode body, String responseName) {
        return body.path(responseName);
    }

    /** Lê String segura */
    public static String getText(JsonNode node, String field) {
        return node.path(field).asText(null);
    }

    /** Lê Long seguro */
    public static Long getLong(JsonNode node, String field) {
        return node.has(field) ? node.path(field).asLong() : null;
    }

}

