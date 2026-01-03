package com.trf5.jus.br.sgc;


import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import com.trf5.jus.br.sgc.domain.entity.Orgao;
import com.trf5.jus.br.sgc.domain.entity.OrgaoUsuario;
import com.trf5.jus.br.sgc.repository.OrgaoRepository;
import com.trf5.jus.br.sgc.repository.OrgaoUsuarioRepository;
import com.trf5.jus.br.sgc.repository.UnidadeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SgcTrf5Application {
    private static UnidadeRepository unidadeRepository;
    private static  OrgaoRepository orgaoRepository;
    private static  OrgaoUsuarioRepository orgaoUsuarioRepository;

    public SgcTrf5Application(UnidadeRepository unidadeRepository,
                              OrgaoRepository orgaoRepository,
                              OrgaoUsuarioRepository orgaoUsuarioRepository) {
        SgcTrf5Application.orgaoRepository = orgaoRepository;
        SgcTrf5Application.orgaoUsuarioRepository = orgaoUsuarioRepository;
        SgcTrf5Application.unidadeRepository = unidadeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SgcTrf5Application.class, args);
        Unidade unidade = new Unidade();
        unidade.setId(1L);
        unidade.setSiglaUnidade("TRF5");
        unidade.setNomeUnidade("Tribunal Regional Federal da 5ª Região");
        unidade.setIdUnidadeGestora(1);
        unidade.setSiglaUnidadeGestora("TRF5");
        unidade.setUnidadeTi(true);
        unidade.setUnidadeTecnica(true);
        unidade.setAtiva(true);
        Orgao orgao = new Orgao();
        orgao.setId(1L);
        orgao.setSigla("TRF5");
        orgao.setSin_ativo(true);
        orgao.setSin_autentica(true);
        orgao.setOrdem(1);
        orgaoRepository.save(orgao);
        unidadeRepository.save(unidade);
        OrgaoUsuario  orgaoUsuario = new OrgaoUsuario();
        List<Unidade> unidades = new ArrayList<>();
        unidades.add(unidade);
        orgaoUsuario.setCodigoOrgao(1);
        orgaoUsuario.setUnidades(unidades);
        orgaoUsuario.setDescricaoOrgao("TRF5");
        orgaoUsuarioRepository.save(orgaoUsuario);



    }
}
