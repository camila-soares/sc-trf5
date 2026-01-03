// package com.trf5.jus.br.sgc.service.impl;

// import com.trf5.jus.br.sgc.config.StorageProperties;

// import com.trf5.jus.br.sgc.service.StorageService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.UUID;

// @Service("localStorageService")
// @RequiredArgsConstructor
// public class LocalStorageServiceImpl implements StorageService {

//     private final StorageProperties props;

//     @Override
//     public String store(MultipartFile file, String relativePath) throws Exception {
//         Path base = Paths.get(props.getLocalBasePath());
//         Path dir = base.resolve(relativePath);
//         Files.createDirectories(dir);
//         String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//         Path target = dir.resolve(filename);
//         file.transferTo(target.toFile());
//         return target.toAbsolutePath().toString();
//     }

//     @Override
//     public String getUrl(String storedPath) {
//         // For local storage we return the absolute path. In production, serve via CDN or controller.
//         return storedPath;
//     }

//     @Override
//     public void delete(String storedPath) throws Exception {
//         Files.deleteIfExists(Paths.get(storedPath));
//     }
// }
