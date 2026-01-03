//package com.trf5.jus.br.sgc.service.impl;
//
//
//import com.trf5.jus.br.sgc.domain.dto.ContratoDTO;
//import com.trf5.jus.br.sgc.domain.entity.Apostilamento;
//import com.trf5.jus.br.sgc.domain.entity.Contrato;
//import com.trf5.jus.br.sgc.domain.entity.Empenho;
//import com.trf5.jus.br.sgc.domain.entity.Aditivo;
//import com.trf5.jus.br.sgc.domain.mapper.ContratoMapper;
//import com.trf5.jus.br.sgc.exceptions.ContratoNotFoundException;
//import com.trf5.jus.br.sgc.repository.ContratoRepository;
//import com.trf5.jus.br.sgc.util.MonetaryUtils;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class ContratoBusinessService {
//
//    private final ContratoRepository contratoRepository;
//    private final ContratoMapper contratoMapper;
//
//    public ContratoBusinessService(ContratoRepository contratoRepository, ContratoMapper contratoMapper) {
//        this.contratoRepository = contratoRepository;
//        this.contratoMapper = contratoMapper;
//    }
//
//    @Transactional
//    public Contrato findOrThrow(Long id) {
//        return contratoRepository.findById(id).orElseThrow(() -> new ContratoNotFoundException(id));
//    }
//
//    public List<ContratoDTO> findAll() {
//        return contratoRepository.findAll()
//                .stream()
//                .map(contratoMapper::toDto)
//                .toList();
//    }
//
//    @Transactional
//    public Contrato createContrato(Contrato contrato) {
//        if (contrato.getValorInicial() == null) {
//            contrato.setValorInicial(MonetaryUtils.zero());
//        }
//        if (contrato.getValorAtual() == null) {
//            contrato.setValorAtual(MonetaryUtils.ofNullable(contrato.getValorInicial()));
//        }
//        return contratoRepository.save(contrato);
//    }
//
//    /**
//     * Recalcula valorAtual do contrato a partir do valor inicial somando os aditivos/apostilamentos.
//     * NOTA: apostilamentos que representam "outras alterações sem valor" (valor null) são ignorados.
//     */
//    @Transactional
//    public Contrato recalculateValorAtual(Long contratoId) {
//        Contrato contrato = findOrThrow(contratoId);
//
//        BigDecimal resultado = MonetaryUtils.ofNullable(contrato.getValorAtual());
//
//        //soma aditivos
//        if(contrato.getTermos() != null) {
//            for (Aditivo aditivo : contrato.getTermos()) {
//                if (aditivo.getValorAlteracao() != null) {
//                    resultado = MonetaryUtils.add(resultado, aditivo.getValorAlteracao());
//                }
//            }
//        }
//
//        //soma apostilamento
//        if (contrato.getApostilamentos() != null) {
//            for (Apostilamento postilamento : contrato.getApostilamentos()) {
//                if (postilamento.getValorAlteracao() != null) {
//                    resultado = MonetaryUtils.add(resultado, postilamento.getValorAlteracao());
//                }
//            }
//        }
//
//        contrato.setValorAtual(resultado);
//        return contratoRepository.save(contrato);
//    }
//
//    /**
//     * Ajuste manual do valorAtual. Obrigatório registrar motivo por auditoria (log externo / audit table).
//     */
//    @Transactional
//    public Contrato manualAdjustValor(Long contratoId, BigDecimal novoValor, String motivo, String usuario) {
//        Contrato contrato = findOrThrow(contratoId);
//        contrato.setValorAtual(MonetaryUtils.ofNullable(novoValor));
//        // TODO: salvar histórico de ajustes (AuditAdjust table). Aqui apenas persistimos.
//        // auditService.recordManualAdjustment(contratoId, contrato.getValorAtual(), motivo, usuario);
//        return contratoRepository.save(contrato);
//    }
//
//    @Transactional
//    public Contrato addNotaEmpenho(Long contratoId, Empenho nota) {
//        Contrato contrato = findOrThrow(contratoId);
//        nota.setContrato(contrato);
//        contrato.getEmpenhos().add(nota);
//        return contratoRepository.save(contrato);
//    }
//
//    /**
//     * Marca contrato como recalculado e retorna.
//     */
//    @Transactional
//    public Contrato applyFullRecalculationAndSave(Long contratoId) {
//        return recalculateValorAtual(contratoId);
//    }
//}
