package com.trf5.jus.br.sgc.service;

import com.trf5.jus.br.sgc.domain.dto.sei.*;
import com.trf5.jus.br.sgc.domain.entity.Documento;

import java.rmi.Remote;

public interface SeiPortType extends Remote {

    /**
     * Geracao de processos
     */
    public RetornoGeracaoProcedimento gerarProcedimento(String siglaSistema, String identificacaoServico,
                                                        String idUnidade,
//                                                        Procedimento procedimento,
                                                        Documento[] documentos,
                                                        String[] procedimentosRelacionados, String[] unidadesEnvio, String sinManterAbertoUnidade, String sinEnviarEmailNotificacao, String dataRetornoProgramado, String diasRetornoProgramado, String sinDiasUteisRetornoProgramado, String idMarcador, String textoMarcador, String dataControlePrazo, String diasControlePrazo, String sinDiasUteisControlePrazo) throws java.rmi.RemoteException;

    /**
     * Geracao de documentos
     */
    public RetornoInclusaoDocumento incluirDocumento(String siglaSistema, String identificacaoServico, String idUnidade, Documento documento) throws java.rmi.RemoteException;

    /**
     * Lista de unidades disponiveis
     */
    public Unidade[] listarUnidades(String siglaSistema, String identificacaoServico, String idTipoProcedimento, String idSerie) throws java.rmi.RemoteException;

    /**
     * Lista de tipos de processo disponiveis
     */
    public TipoProcedimento[] listarTiposProcedimento(String siglaSistema, String identificacaoServico, String idUnidade, String idSerie, String sinIndividual) throws java.rmi.RemoteException;

    /**
     * Lista de usuarios com permissao na unidade
     */
    public Usuario[] listarUsuarios(String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Atribuicao de processo para usuario na unidade
     */
    public String atribuirProcesso(String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String idUsuario, java.lang.String sinReabrir) throws java.rmi.RemoteException;





}
