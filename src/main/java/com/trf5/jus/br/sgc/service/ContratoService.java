package com.trf5.jus.br.sgc.service;

import com.trf5.jus.br.sgc.domain.dto.AditivoDTO;
import com.trf5.jus.br.sgc.domain.dto.ContratoRequest;
import com.trf5.jus.br.sgc.domain.dto.ContratoResponse;
import com.trf5.jus.br.sgc.domain.entity.Contrato;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface ContratoService {
    ContratoResponse criar(ContratoRequest contrato) throws ExecutionException, InterruptedException;
    Contrato atualizar(Integer id, ContratoRequest dto);
    Optional<Contrato> buscarPorId(Integer id);
    List<Contrato> listarTodos();
    AditivoDTO adicionarAditivo(Long contratoId, AditivoDTO dto);
    void encerrarContrato(Integer id, String motivo);
}
