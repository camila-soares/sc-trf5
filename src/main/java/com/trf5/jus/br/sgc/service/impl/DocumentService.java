package com.trf5.jus.br.sgc.service.impl;

import com.trf5.jus.br.sgc.domain.entity.Documento;
import com.trf5.jus.br.sgc.repository.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DocumentService {
    private final DocumentoRepository documentoRepository;

    // regex simples para CPF (formato com pontuação)
    private static final Pattern CPF_PATTERN = Pattern.compile("\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b");
    // regex simples para RG (muito variável; heurística)
    private static final Pattern RG_PATTERN = Pattern.compile("\\b\\d{1,2}\\.\\d{3}\\.\\d{3}-[A-Za-z0-9]\\b");



    public DocumentService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    /**
     * Classifica documento como sensível se o conteúdo (texto extraído) contiver CPF/RG ou outros indícios.
     * IMPORTANTE: esse método é heurístico. Para produção use OCR + PII detection (Ferramentas de DLP).
     */
    public Documento classifyAndPersist(Documento documento, String extractedText) {
        boolean sensivel = false;

        if (extractedText != null && !extractedText.isBlank()) {
            if (CPF_PATTERN.matcher(extractedText).find()) sensivel = true;
            if (RG_PATTERN.matcher(extractedText).find()) sensivel = true;
            // outras heurísticas: emails pessoais, telefones com DDD + número residencial, endereços etc.
        }

      //  documento.setSensivel(sensivel);

        // decide flags de migração conforme LGPD
     //   if (sensivel) {
      //      documento.setMigrarTransparencia(false);
      //      documento.setMigrarPncp(false);
     //   }

        return documentoRepository.save(documento);
    }

}