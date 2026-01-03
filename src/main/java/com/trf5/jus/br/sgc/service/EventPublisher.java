package com.trf5.jus.br.sgc.service;

import com.trf5.jus.br.sgc.domain.entity.Contrato;

public interface EventPublisher {

    void publishContratoAlterado(Long contratoId, String motivo, Long referenciaId);
}
