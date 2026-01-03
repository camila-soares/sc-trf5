package com.trf5.jus.br.sgc.webservice;

import com.trf5.jus.br.sgc.domain.dto.CatalogoMatServDTO;
import com.trf5.jus.br.sgc.domain.dto.PedidoCompraDTO;

import java.util.List;

public interface WebServiceECompras {

    public Boolean unidadeTecnicaUtilizada(Integer idUnidade);
    public PedidoCompraDTO getPedidoCompra(Integer idUnidade, String siglaOrgao);
    public PedidoCompraDTO getPad(Integer idUnidade, String siglaOrgao);
    List<CatalogoMatServDTO> getCatalogoMaterialServico(String texto);
}
