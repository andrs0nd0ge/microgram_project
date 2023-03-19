package com.microgram.project.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    public final static Path root = Paths.get("src/main/resources/static/images");
    @Override
    public void init() {
        try {
            if (!root.toFile().exists()) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save(MultipartFile file) {
        try {
            File convertFile = new File(root.toFile(), Objects.requireNonNull(file.getOriginalFilename()));
            if (!convertFile.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
