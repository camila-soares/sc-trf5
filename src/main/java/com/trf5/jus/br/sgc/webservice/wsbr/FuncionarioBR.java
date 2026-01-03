package com.trf5.jus.br.sgc.webservice.wsbr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioBR {
	
	String matricula;
	String nome;
	String secao;
	String cpf;
	String sexo;
	Date dataNascimento;
	String email;
	String tpSanquineo;
	Date dataDesligamento;
	String codPadrao;
	String codClasse;
	String codNivel;
	String agenciaPcd;
	String cargoDescricao;
	String carreiraCodigo;
	String carreiraDescricao;
	Integer codigoFuncao;
	String codigoFuncionario;
	Integer codigoLotacaoFuncionario;
	Integer codigoLotacaoPai;
	String contaPcd;
	String descricaoFuncao;
	String descricaoLotacao;
	Integer funcionarioCodCargo;
	String nivel;
	String nomeBancoPcd;
	String padrao;
	String telefone;
	String tipoSanguineo;
	String statusServidor;
	
	Integer codCargoFuncao;


}
