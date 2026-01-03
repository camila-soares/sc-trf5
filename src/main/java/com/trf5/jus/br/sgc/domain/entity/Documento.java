package com.trf5.jus.br.sgc.domain.entity;

import com.trf5.jus.br.sgc.domain.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documentos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
//
//    @ManyToOne
//    private Aditivo aditivo;
//
//    @ManyToOne
//    private Apostilamento apostilamento;
}