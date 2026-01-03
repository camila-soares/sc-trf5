// package com.trf5.jus.br.sgc.domain.mapper;
// import com.trf5.jus.br.sgc.domain.dto.ContratoApostilhamentoDTO;
// import com.trf5.jus.br.sgc.domain.entity.Contrato;
// import com.trf5.jus.br.sgc.domain.entity.Apostilamento;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

// @Mapper(componentModel = "spring")
// public interface ContratoApostilhamentoMapper {

//     @Mapping(source = "contrato.id", target = "contractId")
//     ContratoApostilhamentoDTO toDto(Apostilamento entity);

//     @Mapping(source = "contractId", target = "contrato")
//     Apostilamento toEntity(ContratoApostilhamentoDTO dto);

//     default Contrato fromId(Long id) {
//         if (id == null) return null;
//         Contrato c = new Contrato();
//         c.setId(id);
//         return c;
//     }
// }

