// package com.trf5.jus.br.sgc.controller;


// import com.trf5.jus.br.sgc.domain.dto.FornecedorDTO;
// import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
// import com.trf5.jus.br.sgc.service.FornecedoresServices;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.net.URI;

// @RestController
// @RequestMapping("/api/fornecedores")
// public class FornecedoresController {

//     private final FornecedoresServices  fornecedoresServices;
//     private final FornecedorMapper  mapper;

//     public FornecedoresController(FornecedoresServices fornecedoresServices, FornecedorMapper mapper) {
//         this.fornecedoresServices = fornecedoresServices;
//         this.mapper = mapper;
//     }

//     @PostMapping
//     public ResponseEntity<FornecedorDTO>  addFornecedor(@RequestBody FornecedorDTO fornecedorDTO) {
//        FornecedorDTO fornecedor =  fornecedoresServices.addFornecedor(mapper.toEntity(fornecedorDTO));
//        return ResponseEntity.created(URI.create("")).body(fornecedor);

//     }
// }
