//package com.trf5.jus.br.sgc.repository;
//
//
//import com.trf5.jus.br.sgc.domain.entity.Contrato;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.*;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class ContratoRepositoryTest {
//
//    @Autowired
//    private ContratoRepository repository;
//
//    @Test
//    void deveSalvarContrato() {
//        Contrato c = Contrato.builder()
//                .numero("2025-01")
//                .valorTotal(new BigDecimal("5000"))
//                .build();
//
//        Contrato salvo = repository.save(c);
//
//        assertThat(salvo.getId()).isNotNull();
//        assertThat(salvo.getNumero()).isEqualTo("2025-01");
//    }
//}
