package com.microgram.project.util;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void init();
    void save(MultipartFile file);
    void delete(MultipartFile file);
}
