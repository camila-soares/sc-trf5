//package com.trf5.jus.br.sgc.domain.dto.sei;
//
//import com.trf5.jus.br.sgc.domain.entity.Documento;
//import com.trf5.jus.br.sgc.service.SeiPortType;
//import org.apache.axis.client.Stub;
//import org.apache.axis.description.OperationDesc;
//
//import java.net.URL;
//import java.rmi.RemoteException;
//import java.util.Vector;
//
//public class SeiBindingStub extends Stub implements SeiPortType {
//    private Vector cachedSerClasses = new Vector();
//    private Vector cachedSerQNames = new Vector();
//    private Vector cachedSerFactories = new Vector();
//    private Vector cachedDeserFactories = new Vector();
//
//    static OperationDesc [] _operations;
//
//    static {
//        _operations = new org.apache.axis.description.OperationDesc[67];
//        _initOperationDesc1();
//        _initOperationDesc2();
//        _initOperationDesc3();
//        _initOperationDesc4();
//        _initOperationDesc5();
//        _initOperationDesc6();
//        _initOperationDesc7();
//    }
//
//
//    public SeiBindingStub(URL url, Object o) {
//    }
//
//    @Override
//    public RetornoGeracaoProcedimento gerarProcedimento(String siglaSistema, String identificacaoServico, String idUnidade, Documento[] documentos, String[] procedimentosRelacionados, String[] unidadesEnvio, String sinManterAbertoUnidade, String sinEnviarEmailNotificacao, String dataRetornoProgramado, String diasRetornoProgramado, String sinDiasUteisRetornoProgramado, String idMarcador, String textoMarcador, String dataControlePrazo, String diasControlePrazo, String sinDiasUteisControlePrazo) throws RemoteException {
//        return null;
//    }
//
//    @Override
//    public RetornoInclusaoDocumento incluirDocumento(String siglaSistema, String identificacaoServico, String idUnidade, Documento documento) throws RemoteException {
//        return null;
//    }
//
//    @Override
//    public Unidade[] listarUnidades(String siglaSistema, String identificacaoServico, String idTipoProcedimento, String idSerie) throws RemoteException {
//        return new Unidade[0];
//    }
//
//    @Override
//    public TipoProcedimento[] listarTiposProcedimento(String siglaSistema, String identificacaoServico, String idUnidade, String idSerie, String sinIndividual) throws RemoteException {
//        return new TipoProcedimento[0];
//    }
//
//    @Override
//    public Usuario[] listarUsuarios(String siglaSistema, String identificacaoServico, String idUnidade, String idUsuario) throws RemoteException {
//        if (super.cachedEndpoint == null) {
//            throw new org.apache.axis.NoEndPointException();
//        }
//        org.apache.axis.client.Call _call = createCall();
//        _call.setOperation(_operations[31]);
//        _call.setUseSOAPAction(true);
//        _call.setSOAPActionURI("SeiAction");
//        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
//        _call.setOperationName(new javax.xml.namespace.QName("Sei", "listarUsuarios"));
//
//        setRequestHeaders(_call);
//        setAttachments(_call);
//        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {siglaSistema, identificacaoServico, idUnidade, idUsuario});
//
//            if (_resp instanceof java.rmi.RemoteException) {
//                throw (java.rmi.RemoteException)_resp;
//            }
//            else {
//                extractAttachments(_call);
//                try {
//                    return (Usuario[]) _resp;
//                } catch (java.lang.Exception _exception) {
//                    return (Usuario[]) org.apache.axis.utils.JavaUtils.convert(_resp, Usuario[].class);
//                }
//            }
//        } catch (org.apache.axis.AxisFault axisFaultException) {
//            throw axisFaultException;
//        }
//
//    }
//
//    @Override
//    public String atribuirProcesso(String siglaSistema, String identificacaoServico, String idUnidade, String protocoloProcedimento, String idUsuario, String sinReabrir) throws RemoteException {
//        return "";
//    }
//
//    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
//        try {
//            org.apache.axis.client.Call _call = super._createCall();
//            if (super.maintainSessionSet) {
//                _call.setMaintainSession(super.maintainSession);
//            }
//            if (super.cachedUsername != null) {
//                _call.setUsername(super.cachedUsername);
//            }
//            if (super.cachedPassword != null) {
//                _call.setPassword(super.cachedPassword);
//            }
//            if (super.cachedEndpoint != null) {
//                _call.setTargetEndpointAddress(super.cachedEndpoint);
//            }
//            if (super.cachedTimeout != null) {
//                _call.setTimeout(super.cachedTimeout);
//            }
//            if (super.cachedPortName != null) {
//                _call.setPortName(super.cachedPortName);
//            }
//            java.util.Enumeration keys = super.cachedProperties.keys();
//            while (keys.hasMoreElements()) {
//                java.lang.String key = (java.lang.String) keys.nextElement();
//                _call.setProperty(key, super.cachedProperties.get(key));
//            }
//            // All the type mapping information is registered
//            // when the first call is made.
//            // The type mapping information is actually registered in
//            // the TypeMappingRegistry of the service, which
//            // is the reason why registration is only needed for the first call.
//            synchronized (this) {
//                if (firstCall()) {
//                    // must set encoding style before registering serializers
//                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
//                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
//                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
//                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
//                        javax.xml.namespace.QName qName =
//                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
//                        java.lang.Object x = cachedSerFactories.get(i);
//                        if (x instanceof Class) {
//                            java.lang.Class sf = (java.lang.Class)
//                                    cachedSerFactories.get(i);
//                            java.lang.Class df = (java.lang.Class)
//                                    cachedDeserFactories.get(i);
//                            _call.registerTypeMapping(cls, qName, sf, df, false);
//                        }
//                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
//                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
//                                    cachedSerFactories.get(i);
//                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
//                                    cachedDeserFactories.get(i);
//                            _call.registerTypeMapping(cls, qName, sf, df, false);
//                        }
//                    }
//                }
//            }
//            return _call;
//        }
//        catch (java.lang.Throwable _t) {
//            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
//        }
//    }
//
//    private static void _initOperationDesc1(){
//        org.apache.axis.description.OperationDesc oper;
//        org.apache.axis.description.ParameterDesc param;
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("gerarProcedimento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Procedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "Procedimento"), br.jus.trf.sga.dws.sei.Procedimento.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Documentos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfDocumento"), br.jus.trf.sga.dws.sei.Documento[].class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProcedimentosRelacionados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfProcedimentoRelacionado"), java.lang.String[].class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "UnidadesEnvio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfIdUnidade"), java.lang.String[].class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinManterAbertoUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinEnviarEmailNotificacao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "DataRetornoProgramado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "DiasRetornoProgramado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinDiasUteisRetornoProgramado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdMarcador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "TextoMarcador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "DataControlePrazo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "DiasControlePrazo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinDiasUteisControlePrazo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "RetornoGeracaoProcedimento"));
//        oper.setReturnClass(RetornoGeracaoProcedimento.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[0] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("incluirDocumento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Documento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "Documento"), br.jus.trf.sga.dws.sei.Documento.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "RetornoInclusaoDocumento"));
//        oper.setReturnClass(RetornoInclusaoDocumento.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[1] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("listarUnidades");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdTipoProcedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "ArrayOfUnidade"));
//        oper.setReturnClass(Unidade[].class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[2] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("listarTiposProcedimento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdSerie"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinIndividual"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "ArrayOfTipoProcedimento"));
//        oper.setReturnClass(TipoProcedimento[].class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[3] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("listarTiposPrioridade");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "ArrayOfTipoPrioridade"));
//        oper.setReturnClass(TipoPrioridade[].class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[4] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("listarSeries");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdTipoProcedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "ArrayOfSerie"));
//        oper.setReturnClass(Serie[].class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[5] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("listarContatos");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdTipoContato"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PaginaRegistros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PaginaAtual"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sigla"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Nome"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Cpf"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Cnpj"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Matricula"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdContatos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfIdContatos"), java.lang.String[].class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "ArrayOfContato"));
//        oper.setReturnClass(Contato[].class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[6] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("atualizarContatos");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Contatos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfContato"), br.jus.trf.sga.dws.sei.Contato[].class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[7] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("consultarProcedimento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProtocoloProcedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarAssuntos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarInteressados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarObservacoes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarAndamentoGeracao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarAndamentoConclusao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarUltimoAndamento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarUnidadesProcedimentoAberto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarProcedimentosRelacionados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarProcedimentosAnexados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "RetornoConsultaProcedimento"));
//        oper.setReturnClass(RetornoConsultaProcedimento.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[8] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("consultarProcedimentoIndividual");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdOrgaoProcedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdTipoProcedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdOrgaoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "ProcedimentoResumido"));
//        oper.setReturnClass(ProcedimentoResumido.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[9] = oper;
//
//    }
//
//    private static void _initOperationDesc2(){
//        org.apache.axis.description.OperationDesc oper;
//        org.apache.axis.description.ParameterDesc param;
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("consultarDocumento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProtocoloDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarAndamentoGeracao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarAssinaturas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarPublicacao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarCampos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarBlocos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "RetornoConsultaDocumento"));
//        oper.setReturnClass(RetornoConsultaDocumento.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[10] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("cancelarDocumento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProtocoloDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Motivo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[11] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("bloquearDocumento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProtocoloDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[12] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("gerarBloco");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Tipo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Descricao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "UnidadesDisponibilizacao"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfIdUnidade"), java.lang.String[].class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Documentos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("Sei", "ArrayOfDocumentoFormatado"), java.lang.String[].class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinDisponibilizar"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[13] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("consultarBloco");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdBloco"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SinRetornarProtocolos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("Sei", "RetornoConsultaBloco"));
//        oper.setReturnClass(br.jus.trf.sga.dws.sei.RetornoConsultaBloco.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[14] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("excluirBloco");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdBloco"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[15] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("excluirProcesso");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProtocoloProcedimento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[16] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("excluirDocumento");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProtocoloDocumento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[17] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("disponibilizarBloco");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdBloco"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[18] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("cancelarDisponibilizacaoBloco");
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SiglaSistema"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdentificacaoServico"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdUnidade"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "IdBloco"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
//        oper.addParameter(param);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//        oper.setReturnClass(java.lang.String.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "parametros"));
//        oper.setStyle(org.apache.axis.constants.Style.RPC);
//        oper.setUse(org.apache.axis.constants.Use.ENCODED);
//        _operations[19] = oper;
//
//    }
//
//
//
//
//}
