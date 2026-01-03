package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {


    public List<Unidade> findByAtivaIsTrueAndUnidadeTecnicaIsTrueAndSiglaUnidadeGestoraIgnoreCase(
            String siglaUnidadeGestora);

    public List<Unidade> findByAtivaAndUnidadeTecnicaAndSiglaUnidadeGestora(Boolean ativa, Boolean unidadeTecnica,
                                                                            String siglaUnidadeGestora);


}
