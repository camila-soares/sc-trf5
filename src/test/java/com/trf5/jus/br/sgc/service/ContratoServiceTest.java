//package com.trf5.jus.br.sgc.service;
//
//import com.trf5.jus.br.sgc.domain.dto.ContratoRequest;
//import com.trf5.jus.br.sgc.domain.dto.ContratoResponse;
//import com.trf5.jus.br.sgc.domain.entity.Contrato;
//import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
//import com.trf5.jus.br.sgc.domain.mapper.ContratoMapper;
//import com.trf5.jus.br.sgc.repository.AditivosRepository;
//import com.trf5.jus.br.sgc.repository.ContratoRepository;
//import com.trf5.jus.br.sgc.repository.FornecedoresRepository;
//import com.trf5.jus.br.sgc.service.impl.ContratoServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ContratoServiceTest {
//
//    @Mock
//    ContratoMapper contratoMapper;
//
//    @Mock
//    FornecedoresRepository fornecedoresRepository;
//
//    @Mock
//    ContratoRepository contratoRepository;
//
//    @Mock
//    AditivosRepository aditivosRepository;
//
//    @InjectMocks
//    ContratoServiceImpl contratoService;
//
//    ContratoRequest request;
//    Contrato contrato;
//    ContratoResponse response;
//
//    @BeforeEach
//    void setup() {
//
//        request = new ContratoRequest();
//        request.setFornecedorId(10L);
//
//        contrato = new Contrato();
//        Fornecedor fornecedor = new Fornecedor();
//        fornecedor.setId(10L);
//        contrato.setFornecedor(fornecedor);
//
//        response = new ContratoResponse();
//    }
//
//    @Test
//    void deveCriarContratoComSucesso() {
//
//        when(contratoMapper.toEntity(request)).thenReturn(contrato);
//        when(contratoMapper.fromId(10L)).thenReturn(contrato.getFornecedor());
//        when(contratoMapper.toResponse(contrato)).thenReturn(response);
//        when(contratoRepository.save(contrato)).thenReturn(contrato);
//
//        ContratoResponse resultado = contratoService.criar(contrato);
//
//        assertNotNull(resultado);
//        verify(contratoMapper).toEntity(request);
//        verify(contratoRepository).save(contrato);
//    }
//}
