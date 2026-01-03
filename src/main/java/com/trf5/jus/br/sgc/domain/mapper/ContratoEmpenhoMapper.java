// package com.trf5.jus.br.sgc.domain.mapper;

// import com.trf5.jus.br.sgc.domain.dto.ContratoEmpenhoDTO;
// import com.trf5.jus.br.sgc.domain.entity.Contrato;
// import com.trf5.jus.br.sgc.domain.entity.Empenho;
// import org.mapstruct.Mapper;
// import org.mapstruct.ReportingPolicy;

// @Mapper(
//         componentModel = "spring",
//         unmappedTargetPolicy = ReportingPolicy.IGNORE
// )
// public interface ContratoEmpenhoMapper {

//     //@Mapping(source = "contract.id", target = "contractId")
//   //  ContratoEmpenhoDTO toDto(Empenho entity);

//     //@Mapping(source = "contractId", target = "contract")
//    // Empenho toEntity(ContratoEmpenhoDTO dto);

//     default Contrato fromId(Long id) {
//         if (id == null) return null;
//         Contrato c = new Contrato();
//         c.setId(id);
//         return c;
//     }
// }
