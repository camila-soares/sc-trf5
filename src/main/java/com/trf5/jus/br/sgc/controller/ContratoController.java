package com.trf5.jus.br.sgc.controller;


import com.trf5.jus.br.sgc.domain.dto.AditivoDTO;
import com.trf5.jus.br.sgc.domain.dto.ContratoRequest;
import com.trf5.jus.br.sgc.domain.dto.ContratoResponse;
import com.trf5.jus.br.sgc.domain.mapper.ContratoMapper;
import com.trf5.jus.br.sgc.service.ContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/contracts")
public class ContratoController {

    private final ContratoService service;
    private final ContratoMapper mapper;

    public ContratoController(ContratoService service, ContratoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ContratoResponse>  createContrato(@RequestBody ContratoRequest request) throws ExecutionException, InterruptedException {
       ContratoResponse response =  service.criar(request);
       return ResponseEntity.created(URI.create("")).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ContratoResponse>> getAllContrato() {
       List<ContratoResponse> responses = service.listarTodos().stream().map(mapper::toResponse).toList();
       return ResponseEntity.ok(responses);
    }

    @PostMapping("/{id}/aditivos")
    public ResponseEntity<AditivoDTO> adicionarAditivo(@PathVariable Long id, @RequestBody AditivoDTO request) {
        AditivoDTO response = service.adicionarAditivo(id, request);
        return ResponseEntity.ok(response);
    }
}

