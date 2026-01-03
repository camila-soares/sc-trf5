//package com.trf5.jus.br.sgc.config;
//
//import com.trf5.jus.br.sgc.util.Util;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableSw
//public class SGCDocumentacao {
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.trf5.jus.br.sgc.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaData());
//    }
//
//    private ApiInfo  metaData() {
//        return new ApiInfoBuilder()
//                .title("SGC - Documentação API")
//                .description("\"Esta e a documentação da api SGC.\"")
//                .version(Util.getPropMensagem().getString("versao"))
//                .build();
//    }
//    }
