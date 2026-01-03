package com.trf5.jus.br.sgc.service.impl;

import com.trf5.jus.br.sgc.domain.dto.AditivoDTO;
import com.trf5.jus.br.sgc.domain.dto.ContratoRequest;
import com.trf5.jus.br.sgc.domain.dto.ContratoResponse;
import com.trf5.jus.br.sgc.domain.entity.Aditivo;
import com.trf5.jus.br.sgc.domain.entity.Contrato;
import com.trf5.jus.br.sgc.domain.entity.Fornecedor;
import com.trf5.jus.br.sgc.domain.mapper.AditivoMapper;
import com.trf5.jus.br.sgc.repository.AditivosRepository;
import com.trf5.jus.br.sgc.repository.ContratoRepository;
import com.trf5.jus.br.sgc.repository.FornecedoresRepository;
import com.trf5.jus.br.sgc.service.ContratoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ContratoServiceImpl implements ContratoService {

    
    private final FornecedoresRepository fornecedoresRepository;
    private final ContratoRepository contratoRepository;
    private final AditivosRepository aditivosRepository;

    public ContratoServiceImpl( FornecedoresRepository fornecedoresRepository, ContratoRepository contratoRepository, AditivosRepository aditivosRepository) {
      
        this.fornecedoresRepository = fornecedoresRepository;
        this.contratoRepository = contratoRepository;
        this.aditivosRepository = aditivosRepository;
    }

    @Override
    public ContratoResponse criar(ContratoRequest contrato) throws ExecutionException, InterruptedException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(contrato.getFornecedorNome());
        fornecedor.setCnpj(contrato.getFornecedorCnpj());
         fornecedoresRepository.save(fornecedor);
        Contrato contrato1 = getContrato(contrato, fornecedor);
        contratoRepository.save(contrato1);
        return new ContratoResponse();
    }

    private static Contrato getContrato(ContratoRequest contrato, Fornecedor fornecedor) {
        Contrato contrato1 = new Contrato();
        contrato1.setNumeroContrato(Integer.valueOf(contrato.getNumeroContrato()));
        contrato1.setAno(contrato.getAno());

        contrato1.setAta(contrato.getAta());
        contrato1.setNumeroPregao(contrato.getNumeroPregao());
        //contrato1.setN(contrato.getNumeroPA());
        contrato1.setModeloContrato(contrato.getModeloContrato());
        contrato1.setModalidadeFundamento(contrato.getModalidadeFundamento());
        contrato1.setObjetoDescricao(contrato.getObjeto());
        contrato1.setNumeroProcessoSei(contrato.getProcesso());
       // contrato1.setVi(contrato.getProcessosVinculados());
        contrato1.setFornecedor(fornecedor);
        //  contrato1.getFornecedor().setNome(contrato.getFornecedorNome());
        contrato1.setDataAssinatura(contrato.getDataAssinatura());
        contrato1.setVigenciaInicio(contrato.getDataInicioVigencia());
        contrato1.setVigenciaFim(contrato.getDataFimVigencia());
        contrato1.setProrrogavel(contrato.getProrrogavel());
        contrato1.setProrrogacaoMeses(contrato.getPrazo());
        contrato1.setStatus(contrato.getStatus());
       // contrato1.(contrato.getFiscalizacao());
       // contrato1.setDadosPublicacao(contrato.getDadosPublicacao());
        contrato1.setValorAtual(contrato.getValorAtualAnual());
        contrato1.setValorInicial(contrato.getValorInicial());
        return contrato1;
    }

//    private static void validaVirgencia(Contrato contrato) {
//        if (!contrato.validarVigencia()) {
//            throw new RuntimeException("A vigência é inválida");
//        }
//    }

    @Override
    public Contrato atualizar(Integer id, ContratoRequest dto) {
        return null;
    }

    @Override
    public Optional<Contrato> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();

    }

    @Override
    @Transactional
    public AditivoDTO adicionarAditivo(Long contratoId, AditivoDTO request) {

        Contrato contrato = contratoRepository.findById(contratoId).orElseThrow(
                () -> new RuntimeException("Contrato não encontrado"));
        Aditivo aditivo = AditivoMapper.toEntity(request, contrato);
        aditivo.setContrato(contrato);
        if (aditivo.getValor() != null) {
            BigDecimal current = contrato.getValorInicial() != null ? aditivo.getValor() : BigDecimal.ZERO;
            BigDecimal novo = current.add(aditivo.getNovoValor());
            contrato.setValorAtual(novo);
            //contrato.atualizarStatus();
            contratoRepository.save(contrato);
        }
      //  verificarTipoAditivos(aditivo);

        Aditivo salvo = aditivosRepository.save(aditivo);
        return AditivoMapper.toDTO(salvo);
    }


    @Override
    public void encerrarContrato(Integer id, String motivo) {

    }

//    private static void verificarTipoAditivos(Aditivo aditivo) {
//        //Executa regra de negócio
//        switch (aditivo.getTipo()) {
//            case ACRESCIMO -> aditivo.aplicarAcrescimo();
//            case SUPRESSAO -> aditivo.aplicarSupressao();
//            case REAJUSTE -> {
//                aditivo.validarPercentual();
//                aditivo.aplicarReajuste();
//            }
//            case PRORROGACAO -> aditivo.aplicarReajuste();
//        }
//    }

  }
