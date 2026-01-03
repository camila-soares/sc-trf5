// package com.trf5.jus.br.sgc.domain.mapper;

// import com.trf5.jus.br.sgc.domain.dto.ContratoTermoAditivoDTO;
// import com.trf5.jus.br.sgc.domain.entity.Aditivo;
// import com.trf5.jus.br.sgc.domain.entity.Contrato;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

// @Mapper(componentModel = "spring")
// public interface ContratoTermoAditivoMapper {

//     @Mapping(source = "contrato.id", target = "contractId")
//     ContratoTermoAditivoDTO toDto(Aditivo entity);

//     @Mapping(source = "contractId", target = "contrato")
//     Aditivo toEntity(ContratoTermoAditivoDTO dto);

//     default Contrato fromId(Long id) {
//         if (id == null) return null;
//         Contrato c = new Contrato();
//         c.setId(id);
//         return c;
//     }
// }

