// package com.trf5.jus.br.sgc.domain.mapper;

// import com.trf5.jus.br.sgc.domain.dto.ContratoDocumentDTO;
// import com.trf5.jus.br.sgc.domain.entity.Aditivo;
// import com.trf5.jus.br.sgc.domain.entity.Contrato;
// import com.trf5.jus.br.sgc.domain.entity.Apostilamento;
// import com.trf5.jus.br.sgc.domain.entity.Documento;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

// @Mapper(componentModel = "spring")
// public interface ContratoDocumentoMapper {

//     @Mapping(source = "contrato.id", target = "contrato")
//     @Mapping(source = "sensivel", target = "sensivel")
//    // @Mapping(source = "sensivel", target = "apostilleId")
//     ContratoDocumentDTO toDto(Documento entity);

//     @Mapping(source = "contrato", target = "contrato")
//    // @Mapping(source = "sensivel", target = "amendment")
//    // @Mapping(source = "apostilleId", target = "apostille")
//     Documento toEntity(ContratoDocumentDTO dto);

//     default Contrato fromContractId(Long id) {
//         if (id == null) return null;
//         Contrato c = new Contrato();
//         c.setId(id);
//         return c;
//     }

//     default Aditivo
//     fromAmendmentId(Long id) {
//         if (id == null) return null;
//         Aditivo a = new Aditivo();
//         a.setId(id);
//         return a;
//     }

//     default Apostilamento fromApostilleId(Long id) {
//         if (id == null) return null;
//         Apostilamento a = new Apostilamento();
//         a.setId(id);
//         return a;
//     }
// }

