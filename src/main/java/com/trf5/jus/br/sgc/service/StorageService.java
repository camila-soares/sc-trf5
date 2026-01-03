package com.trf5.jus.br.sgc.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    // store file, return the storage path (url or object key)
    String store(MultipartFile file, String relativePath) throws Exception;
    // get a presigned URL or path to download
    String getUrl(String storedPath);
    // delete
    void delete(String storedPath) throws Exception;
}
