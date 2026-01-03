package com.trf5.jus.br.sgc.domain.mapper;

import com.trf5.jus.br.sgc.domain.dto.AditivoDTO;
import com.trf5.jus.br.sgc.domain.entity.Aditivo;
import com.trf5.jus.br.sgc.domain.entity.Contrato;

public class AditivoMapper {

    public static Aditivo toEntity(AditivoDTO dto, Contrato contrato) {
        return Aditivo.builder()

                .contrato(contrato)
                .contrato(contrato)
               // .numeroPA(dto.getNumeroPA())
               // .numeroPAD(dto.getNumeroPAD())
                .dataAssinatura(dto.getDataAssinatura())
                .tipo(dto.getTipo())
               // .descricao(dto.getDescricao())
                .dataAssinatura(dto.getDataInicio())
                //.vigenteAte(dto.getVigenteAte())
                //.valorReajuste(dto.getValorTotal())
                .percentual(dto.getPercentual())
                //.demanda(dto.getDemanda())
               // .prazo(dto.getPrazo())
                .unidadePrazo(dto.getUnidadePrazo())
               // .valorReajuste(dto.getValorReajuste())
               // .prazoDevolvido(dto.getPrazoDevolvido())
              //  .novaVigencia(dto.getNovaVigencia())
                .build();
    }

    public static AditivoDTO toDTO(Aditivo a) {
        AditivoDTO dto = new AditivoDTO();
        dto.setId(a.getId());
       // dto.setContratoId(a.getContrato().getId());
      //  dto.setNumero(a.getContrato().getNumeroContrato());
      //  dto.setNumeroPA(a.getContrato().getNumeroPA());
     //   dto.setNumeroPAD(a.getContrato().getNumeroPregao());
        dto.setDataAssinatura(a.getDataAssinatura());
        dto.setTipo(a.getTipo());
       // dto.setDescricao(a.getDescricao());
        dto.setDataInicio(a.getDataAssinatura());
       // dto.setVigenteAte(a.getVigenteAte());
       // dto.setValorTotal(a.getValorTotal());
        dto.setPercentual(a.getPercentual());
     //   dto.setDemanda(a.getDemanda());
      //  dto.setPrazo(a.getPrazo());
        dto.setUnidadePrazo(a.getUnidadePrazo());
     //   dto.setValorReajuste(a.getValorReajuste());
     //   dto.setPrazoDevolvido(a.getPrazoDevolvido());
     //   dto.setNovaVigencia(a.getNovaVigencia());
        return dto;
    }
}
