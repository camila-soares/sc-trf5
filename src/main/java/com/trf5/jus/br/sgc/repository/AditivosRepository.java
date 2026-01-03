package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.Aditivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AditivosRepository extends JpaRepository<Aditivo, Long> {

    List<Aditivo> findByContratoId(Long contratoId);
}
