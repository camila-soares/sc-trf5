package com.trf5.jus.br.sgc.webservice.wsbr;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.trf5.jus.br.sgc.domain.dto.FuncionarioBR;
import com.trf5.jus.br.sgc.util.Util;

import com.sun.jersey.api.client.ClientResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.MediaType;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebServiceBR {

    public FuncionarioBR buscaFuncionarioPorLoginRede(String loginRede){
		Client client = Client.create();
//		String path = Util.getPropProjeto().getString("link_BRWS");
        String servico = "/WSChecarLoginRede/"+ loginRede;
		FuncionarioBR funcionario = null;

		try{

            WebResource response = client.resource(Util.getPropProjeto().getString("link_BRWS").concat(servico));
            ClientResponse response1 = response.accept("application/json").get(ClientResponse.class);

			if(response1.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatus());
			}

			
			String output = response1.getEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            funcionario = mapper.readValue(output, FuncionarioBR.class);
	
			/*Gson g = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject json = (JsonObject) parser.parse(output);*/
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return funcionario;
	}
	
	public  FuncionarioBR buscaFuncionarioPorMatricula(String matricula){
		
		String path = Util.getPropProjeto().getString("link_wsbr");
        String servico = "/WSChecarLogin/"+ matricula;
		FuncionarioBR  funcionario = null;

		try{
			Client client = Client.create();


            WebResource webResource = client.resource(Util.getPropProjeto().getString("link_BRWS").concat(servico));
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);


            if(response.getStatus() != 200){
				throw new RuntimeException( "Failed : HTTP error code : "
				+ response.getStatus());
			}
			
			String output = response.getEntity(String.class);
	
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			funcionario = mapper.readValue(output, FuncionarioBR.class);

		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return funcionario;
	}

    public static String consultarChaveAcesso(String siglaSitemaSeiSip, String siglaSitema) {

        String servico = "WSIntegracaoSEI/TRF5/" + siglaSitemaSeiSip + "/" + siglaSitema;
        String chaveAcesso;

        try {
            Client client = Client.create();
            String url = Util.getPropProjeto().getString("link_BRWS").concat(servico);

            WebResource webResource = client.resource(url);

            ClientResponse response = webResource
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Erro ao realizar consulta ao Web Service BR");
            }

            String output = response.getEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            String[] chaves = mapper.readValue(output, String[].class);

            if (chaves == null || chaves.length == 0 || chaves[0] == null || chaves[0].trim().isEmpty()) {
                throw new RuntimeException(
                        Util.getPropMensagem().getString("erro.wsbr.chave.acesso.sei.nao.encontrada")
                );
            }

            chaveAcesso = chaves[0];

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao realizar consulta ao WebService BR", e);
        }

        return chaveAcesso;
    }

}
