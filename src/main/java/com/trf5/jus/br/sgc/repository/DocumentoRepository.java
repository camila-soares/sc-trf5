package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
