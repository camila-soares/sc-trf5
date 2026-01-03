package com.trf5.jus.br.sgc.webservice.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trf5.jus.br.sgc.domain.dto.CatalogoMatServDTO;
import com.trf5.jus.br.sgc.domain.dto.PedidoCompraDTO;
import com.trf5.jus.br.sgc.util.Util;
import com.trf5.jus.br.sgc.webservice.WebServiceECompras;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class WebServiceEComprasImpl implements WebServiceECompras {

    private static final String ECOMPRAS = "eCompras";

    public WebClient webClientEcompas;

    public WebServiceEComprasImpl() {
        webClientEcompas = WebClient.builder().baseUrl(Util.getPropProjeto().getString(ECOMPRAS))
                .defaultHeaders(httpHeaders -> addDefaultHeaders(httpHeaders)).build();
    }

    @Override
    public Boolean unidadeTecnicaUtilizada(Integer idUnidade) {
        log.info("Verificando se a unidade técnica {} utiliza eCompras.", idUnidade);
        log.info(Util.getPropProjeto().getString(ECOMPRAS)+"/unidadeTecnicaUtilizada/{}", idUnidade);
        Mono<String> contrato = webClientEcompas.get()
                .uri(Util.getPropProjeto().getString(ECOMPRAS)+"/unidadeTecnicaUtilizada/{}", idUnidade)
                .retrieve()
                .bodyToMono(String.class).timeout(Duration.ofMillis(55_000));

                if (contrato.block() == null) {
                    log.error("Contrato não encontrado para a unidade técnica: {}", idUnidade);
                    return false;
                }
                return contrato.block().equals("true");
    }

    @Override
    public PedidoCompraDTO getPedidoCompra(Integer idUnidade, String siglaOrgao) {
        log.info(Util.getPropProjeto().getString(ECOMPRAS)+"/pedidoCompra/"+idUnidade+"/orgao/"+siglaOrgao);
        Mono<PedidoCompraDTO> pedidoCompra = webClientEcompas.get()
                .uri(Util.getPropProjeto().getString(ECOMPRAS)+"/pedidoCompra/"+idUnidade+"/orgao/"+siglaOrgao)
                .retrieve()
                .bodyToMono(PedidoCompraDTO.class).timeout(Duration.ofMillis(55_000));
        return pedidoCompra.block();
    }

    @Override
    public PedidoCompraDTO getPad(Integer idUnidade, String siglaOrgao) {
        log.info(Util.getPropProjeto().getString(ECOMPRAS)+"/pad/"+"/orgao/"+siglaOrgao);
        Mono<PedidoCompraDTO> pedidoCompra = webClientEcompas.get()
                .uri(Util.getPropProjeto().getString(ECOMPRAS)+"/pad/"+"/orgao/"+siglaOrgao)
                .retrieve()
                .bodyToMono(PedidoCompraDTO.class).timeout(Duration.ofMillis(55_000));
        return pedidoCompra.block();
    }

    @Override
    public List<CatalogoMatServDTO> getCatalogoMaterialServico(String texto) {
        if (Util.isNullOrEmpty(texto)) {
            return List.of();
        }

        log.info(Util.getPropProjeto().getString(ECOMPRAS) + "/catalogo/{}", texto);
        
        String resourceUrl = Util.getPropProjeto().getString(ECOMPRAS) + "/catalogo/" + texto;
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setObjectMapper(mapper);
            restTemplate.getMessageConverters().add(0, converter);
            
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + Util.getPropProjeto().getString("tokenEcompras"));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<CatalogoMatServDTO[]> response = restTemplate
                    .exchange(resourceUrl, HttpMethod.GET, entity, CatalogoMatServDTO[].class);
            
            return response.getBody() != null ? List.of(response.getBody()) : new ArrayList<>();
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                log.warn("Nenhum catálogo encontrado para o texto: {}", texto);
            } else {
                log.error("Erro ao acessar o serviço de eCompras: {}", e.getMessage());
                throw new RuntimeException("Erro ao consultar o serviço do eCompras: codigo " + e.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Erro ao acessar o serviço de eCompras: {}", e.getMessage());
        }
        
        return new ArrayList<>();
    }


    private void addDefaultHeaders(final HttpHeaders headers) {
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + Util.getPropProjeto().getString("tokenEcompras"));
    }

}
