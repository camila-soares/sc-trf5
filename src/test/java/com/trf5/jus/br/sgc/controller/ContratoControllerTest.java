//package com.trf5.jus.br.sgc.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.trf5.jus.br.sgc.domain.dto.ContratoRequest;
//import com.trf5.jus.br.sgc.domain.dto.ContratoResponse;
//import com.trf5.jus.br.sgc.domain.dto.FornecedorDTO;
//import com.trf5.jus.br.sgc.domain.entity.Contrato;
//import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
//import com.trf5.jus.br.sgc.service.ContratoService;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import org.springframework.test.web.servlet.MockMvc;
//import java.math.BigDecimal;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ContratoController.class)
//class ContratoControllerTest {
//
//    @Autowired private MockMvc mvc;
//    @Autowired private ObjectMapper mapper;
//
//    @MockBean private ContratoService service;
//
//    @Test
//    void deveCriarContrato() throws Exception {
//
//        Contrato dto = Contrato.builder()
//                .numeroContrato("XY-55")
//                .objeto("Serviço de TI")
//                .valorGlobal(new BigDecimal("9000"))
//                .fornecedor(Fornecedor.builder().id(1L).build())
//                .build();
//
//        ContratoResponse contrato = ContratoResponse.builder()
//                .numero("XY-55")
//                .fornecedor(FornecedorDTO.builder().nome("Empresa A").build())
//                .valorTotal(new BigDecimal("9000"))
//                .build();
//
//        when(service.criar(dto)).thenReturn(contrato);
//
//        mvc.perform(post("/api/contratos")
//                        .contentType("application/json")
//                        .content(mapper.writeValueAsString(dto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.numero").value("XY-55"))
//                .andExpect(jsonPath("$.fornecedorNome").value("Empresa A"));
//    }
//}
//
