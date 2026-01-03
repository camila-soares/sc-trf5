//package com.trf5.jus.br.sgc.service.impl;
//
//import com.trf5.jus.br.sgc.domain.dto.AditivoDTO;
//import com.trf5.jus.br.sgc.domain.entity.Aditivo;
//import com.trf5.jus.br.sgc.domain.mapper.AditivoMapper;
//import com.trf5.jus.br.sgc.repository.AditivosRepository;
//import com.trf5.jus.br.sgc.repository.ContratoRepository;
//import com.trf5.jus.br.sgc.service.ContractAmendmentService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class AditivoServiceImpl implements ContractAmendmentService.AditivoService {
//
//    private final AditivosRepository aditivoRepository;
//    private final ContratoRepository contratoRepository;
//
//    public AditivoServiceImpl(AditivosRepository aditivoRepository, ContratoRepository contratoRepository) {
//        this.aditivoRepository = aditivoRepository;
//        this.contratoRepository = contratoRepository;
//    }
//
//
//    @Override
//    public List<AditivoDTO> listarPorContrato(Long contratoId) {
//        return aditivoRepository.findByContratoId(contratoId).stream().map(AditivoMapper::toDTO).toList();
//    }
//
//    @Override
//    public AditivoDTO buscar(Long id) {
//        Aditivo ad = aditivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aditivo não encontrado"));
//        return AditivoMapper.toDTO(ad);
//    }
//
//    @Override
//    public void deletar(Long id) {
//        aditivoRepository.deleteById(id);
//    }
//}
