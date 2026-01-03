//package com.trf5.jus.br.sgc.controller;
//
//import com.trf5.jus.br.sgc.domain.dto.AditivoDTO;
//import com.trf5.jus.br.sgc.service.ContractAmendmentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/aditivos")
//public class AditivoController {
//
//    private final ContractAmendmentService.AditivoService service;
//
//    public AditivoController(ContractAmendmentService.AditivoService service) {
//        this.service = service;
//    }
//
////    @PostMapping
////    public AditivoDTO criar(@RequestBody AditivoDTO dto) {
////        return service.criar(dto);
////    }
//
//    @GetMapping("/contrato/{contratoId}")
//    public List<AditivoDTO> listarPorContrato(@PathVariable Long contratoId) {
//        return service.listarPorContrato(contratoId);
//    }
//
//    @GetMapping("/{id}")
//    public AditivoDTO buscar(@PathVariable Long id) {
//        return service.buscar(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletar(@PathVariable Long id) {
//        service.deletar(id);
//    }
//}
