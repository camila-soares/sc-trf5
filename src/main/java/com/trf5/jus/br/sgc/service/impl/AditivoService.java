package com.trf5.jus.br.sgc.service.impl;


import com.trf5.jus.br.sgc.domain.entity.Contrato;
import com.trf5.jus.br.sgc.domain.entity.Aditivo;
import com.trf5.jus.br.sgc.repository.AditivosRepository;
import com.trf5.jus.br.sgc.repository.ContratoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AditivoService {

    private final AditivosRepository  aditivosRepository;
    private  final ContratoRepository contratoRepository;
   // private final ContratoBusinessService contratoBusinessService;

    public AditivoService(AditivosRepository aditivosRepository, ContratoRepository contratoRepository) {
        this.aditivosRepository = aditivosRepository;
        this.contratoRepository = contratoRepository;
    }

    @Transactional
    public Aditivo createAndApply(Aditivo aditivo) {
        //periste aditivo
        Aditivo termo = aditivosRepository.save(aditivo);

        //atualiza contrato: recaulcula usando a regra de negocio centroall
        Contrato contrato = contratoRepository.findById(aditivo.getContrato().getId()).orElseThrow(() -> new IllegalStateException("Contrato não encontrado"));

        //garantia: persistimos a nova lista de contrato(bidirecional)
        contrato.getAditivos().add(termo);
        contratoRepository.save(contrato);

        //recalcula o valor de contrato(centralizado)
       // contratoBusinessService.recalculateValorAtual(contrato.getId());

        //publica evento para a transparencia/pncp (assincrono)
        ///TODO  eventPublisher.publishContratoAlterado(contrato.getId(), "ADITIVO", saved.getId());
        return aditivo;
    }

    //private final EventPublisher
}
