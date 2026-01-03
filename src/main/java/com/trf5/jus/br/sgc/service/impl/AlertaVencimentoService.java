//package com.trf5.jus.br.sgc.service.impl;
//
//
//import com.trf5.jus.br.sgc.domain.entity.Contrato;
//import com.trf5.jus.br.sgc.repository.ContratoRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class AlertaVencimentoService {
//
//    private static final Logger log = LoggerFactory.getLogger(AlertaVencimentoService.class);
//
//    private final ContratoRepository contratoRepository;
//
//    public AlertaVencimentoService(ContratoRepository contratoRepository) {
//        this.contratoRepository = contratoRepository;
//    }
//
//    @Scheduled(cron = "0 0 0 * * *")
//    public void dailyVencimentoCheck(){
//        LocalDate hoje = LocalDate.now();
//        LocalDate limit = hoje.plusDays(30);
//
//        List<Contrato> proximos =  contratoRepository.findAll()
//                .stream()
//                .filter(c -> c.getFimvigencia() != null && !c.getFimvigencia().isBefore(hoje) && !c.getFimvigencia().isAfter(limit)).toList();
//
//        for(Contrato contrato : proximos){
//            /// TODO notificar - por email FCM, criar task no sistema de gestao
//            log.info("Notificação de venciemento enviada para contrato {} - fim {}", contrato.getNumero(), contrato.getFimvigencia());
//
//        }
//    }
//}
