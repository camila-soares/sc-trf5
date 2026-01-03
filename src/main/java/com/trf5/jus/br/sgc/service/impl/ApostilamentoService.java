//package com.trf5.jus.br.sgc.service.impl;
//
//
//import com.trf5.jus.br.sgc.domain.entity.Apostilamento;
//import com.trf5.jus.br.sgc.domain.entity.Contrato;
//import com.trf5.jus.br.sgc.exceptions.ContratoNotFoundException;
//import com.trf5.jus.br.sgc.repository.ApostilamentoRepository;
//import com.trf5.jus.br.sgc.repository.ContratoRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ApostilamentoService {
//
//    private final ApostilamentoRepository  apostilamentoRepository;
//    private final ContratoRepository contratoRepository;
//    private final ContratoBusinessService contratoBusinessService;
//
//    /// TODO private final EventPublisher eventPublisher;
//    public ApostilamentoService(ApostilamentoRepository apostilamentoRepository, ContratoRepository contratoRepository, ContratoBusinessService contratoBusinessService) {
//        this.apostilamentoRepository = apostilamentoRepository;
//        this.contratoRepository = contratoRepository;
//        this.contratoBusinessService = contratoBusinessService;
//    }
//
//    @Transactional
//    public Apostilamento createAndApply(Apostilamento apostilamento) {
//        Apostilamento apostilamento1 = apostilamentoRepository.save(apostilamento);
//
//        Contrato contrato1 = contratoRepository.findById(apostilamento1.getContrato().getId()).orElseThrow(() -> new ContratoNotFoundException(apostilamento1.getContrato().getId()));
//
//        contrato1.getApostilamentos().add(apostilamento);
//        contratoRepository.save(contrato1);
//
//        //recalcula o valor do contrato
//        contratoBusinessService.recalculateValorAtual(contrato1.getId());
//        /// TODO // Fire event
//        ///         eventPublisher.publishContratoAlterado(contrato.getId(), "APOSTILAMENTO", saved.getId());
//        return apostilamento;
//    }
//
//}
