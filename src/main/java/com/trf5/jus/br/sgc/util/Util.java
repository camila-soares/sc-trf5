package com.trf5.jus.br.sgc.util;

import com.trf5.jus.br.sgc.domain.enums.SecaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Util {

    private static ResourceBundle propProjeto = ResourceBundle.getBundle("webService");

    public static ResourceBundle getPropProjeto() {
        if (propProjeto == null)
            propProjeto = ResourceBundle.getBundle("webService");
        return propProjeto;
    }

    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        is.setEncoding("UTF-8");
        return builder.parse(is);
    }
    private static ResourceBundle propMensagem = ResourceBundle
            .getBundle("messages");

    public static ResourceBundle getPropMensagem() {
        if (propMensagem == null)
            propMensagem = ResourceBundle.getBundle("messages");
        return propMensagem;
    }

    public static Page<?> listToPage(List<?> list, Pageable pageable) {
        int page = pageable.getPageNumber();
        int max = Math.min(pageable.getPageSize() * (page + 1), list.size());
        Page<?> pageObjects;
        pageObjects = new PageImpl<>(list.subList(page*pageable.getPageSize(), max), pageable, list.size());
        return pageObjects;
    }

    public static boolean isNullOrEmpty(String valor){
        return valor == null || valor.isEmpty();
    }

    public static boolean isNullOrEmpty(Object objeto) {
        return objeto == null || objeto.toString().trim().isEmpty();
    }

    public static String removerAcentos(String valorAcentuado){
        valorAcentuado = Normalizer.normalize(valorAcentuado, Normalizer.Form.NFD);
        valorAcentuado = valorAcentuado.replaceAll("[^\\p{ASCII}]", "");
        return valorAcentuado;
    }

    public static String getSecao(String secao, String retorno) {
        for (SecaoEnum secaoEnum : SecaoEnum.values()) {
            if(secao.equalsIgnoreCase(secaoEnum.name())) {
                if(retorno.equalsIgnoreCase("CODIGO")) {
                    return secaoEnum.getCodigo();
                }else {
                    return secaoEnum.getSigla();
                }
            }
        }
        return null;
    }

    public static String getOrgao(String secao) {
        for (SecaoEnum secaoEnum : SecaoEnum.values()) {
            if (secao.equalsIgnoreCase(secaoEnum.getCodigo())) {
                return secaoEnum.getOrgao();
            }
        }
        return null;
    }

    public static String getNomeDoMes(int i, int tipo) {
        String[] mes = {"Janeiro", "Fevereiro", "Março", "Abril",
                "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
                "Novembro", "Dezembro"};

        if (tipo == 0) {
            return(mes[i-1]); // extenso
        }else {
            return(mes[i-1].substring(0, 3)); // abreviado
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null || strNum.isBlank()) {
            return false;
        }

        try {
            Double.parseDouble(strNum);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static Date stringToDate(String data) throws Exception {
        if (data == null || data.isEmpty())
            return null;
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = (java.util.Date) formatter.parse(data);
        return date;
    }

    public static String converterQuantidade(BigDecimal valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale ("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.000",symbols);
        return df.format(valor);
    }

    public static String converterValor(BigDecimal valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00",symbols);
        return df.format(valor);
    }

    public static String converterDateToString(Date data) {
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dataFormatada.format(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
