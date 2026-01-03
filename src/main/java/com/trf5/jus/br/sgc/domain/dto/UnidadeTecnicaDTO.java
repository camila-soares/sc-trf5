package com.trf5.jus.br.sgc.domain.dto;


import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import com.trf5.jus.br.sgc.domain.dto.sei.UnidadeResumoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeTecnicaDTO {

    private Integer id;
    private String siglaUnidade;
    private String nomeUnidade;
    private Integer idUnidadeGestora;
    private String siglaUnidadeGestora;
    private Boolean unidadeTecnica;


    public UnidadeTecnicaDTO(Unidade unidade) {
        this.id = Math.toIntExact(unidade.getId());
        this.siglaUnidade = unidade.getSiglaUnidade();
        this.nomeUnidade = unidade.getNomeUnidade();
        this.idUnidadeGestora = unidade.getIdUnidadeGestora();
        this.siglaUnidadeGestora = unidade.getSiglaUnidadeGestora();
        this.unidadeTecnica = unidade.getUnidadeTecnica();
    }


    public static Page<UnidadeResumoDTO> converter(Page<Unidade> unidades) {
        return unidades.map(UnidadeResumoDTO::new);
    }
}
