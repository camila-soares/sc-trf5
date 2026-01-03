package com.trf5.jus.br.sgc.domain.dto;


import com.trf5.jus.br.sgc.domain.entity.Contrato;
import com.trf5.jus.br.sgc.domain.enums.TipoDocumento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ContratoDocumentDTO {

    private String nomeArquivo;        // nome físico
    private String nomeOriginal;       // nome enviado
    private String url;                // se usar S3
    private Boolean migrarTransparencia;
    private Boolean migrarPncp;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo;

    // classifica automaticamente o que NÃO deve ir pro Portal (LGPD)
    private Boolean sensivel; // true = não enviar para transparência

    @ManyToOne
    private Contrato contrato;
}

