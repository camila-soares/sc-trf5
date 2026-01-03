package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FornecedoresRepository extends JpaRepository<Fornecedor, Long> {


    Optional<Fornecedor> findByCnpj(String cpfCnpj);
}
