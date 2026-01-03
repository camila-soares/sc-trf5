package com.trf5.jus.br.sgc.domain.dto.sei;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnidadeResumoDTO {

    private Integer id;
    private String siglaUnidade;
    private String nomeUnidade;
    private Integer idUnidadeGestora;
    private String siglaUnidadeGestora;
    private Boolean unidadeTecnica;


    public UnidadeResumoDTO(Unidade unidade) {
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
