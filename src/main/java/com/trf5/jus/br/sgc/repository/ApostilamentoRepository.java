package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.Apostilamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostilamentoRepository extends JpaRepository<Apostilamento, Long> {
}
