package com.trf5.jus.br.sgc.domain.mapper;

import com.trf5.jus.br.sgc.domain.dto.ContratoRequest;
import com.trf5.jus.br.sgc.domain.dto.ContratoResponse;
import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import com.trf5.jus.br.sgc.domain.entity.Contrato;
import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

@Mapper(
        componentModel = "spring"
)
public interface ContratoMapper {

    @Mapping(target = "fornecedor", source = "fornecedorCnpj")
    @Mapping(target = "unidade", source = "unidade")
    Contrato toEntity(ContratoRequest dto);

    @Mapping(target = "fornecedor", source = "fornecedor")
   // @Mapping(target = "fornecedorCnpj", source = "fornecedor.cpfCnpj")
    //@Mapping(target = "diasRestantes", expression = "java(entity.calcularDiasRestantes())")
    ContratoResponse toResponse(Contrato entity);

    // --- Helper ---
    default Fornecedor fromCnpj(String cnpj) {
        if (cnpj == null) return null;
        Fornecedor f = new Fornecedor();
        f.setCnpj(cnpj);
        return f;
    }

    // 👇 método auxiliar que o MapStruct usará automaticamente
    default Unidade map(String value) {
        if (value == null) return null;
        Unidade ut = new Unidade();
        ut.setCridoEm(Instant.parse(value)); // ou setId, depende do seu modelo
        return ut;
    }

    // 👇 método inverso (opcional)
    default String map(Unidade value) {
        return value != null ? value.getNomeUnidade() : null;
    }
}

