package com.easyfood.files.service;

import com.easyfood.files.persistence.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    void store(MultipartFile file) throws IOException;
    FileDB getFile();

}
