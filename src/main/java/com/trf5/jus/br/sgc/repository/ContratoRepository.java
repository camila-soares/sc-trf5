package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.Contrato;
import com.trf5.jus.br.sgc.domain.enums.StatusContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByStatus(StatusContrato status);

    List<Contrato> findByFornecedorId(Long fornecedorId);

  //  List<Contrato> findByNumeroContainingIgnoreCase(String numero);
}
