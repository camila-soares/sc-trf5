package com.trf5.jus.br.sgc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String type = "local"; // local, minio, s3
    private String localBasePath = "/var/sgc/uploads";
    private String endpoint;

}
