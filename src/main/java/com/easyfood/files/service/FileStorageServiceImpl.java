package com.easyfood.files.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import com.easyfood.files.persistence.FileDB;
import com.easyfood.files.repository.FileDBRepository;
import com.easyfood.security.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageServiceImpl implements FileStorageService{

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public void store(MultipartFile file) throws IOException {
        Optional<FileDB> fileFromDB = fileDBRepository.findByUsername(getUsername());
        if (!fileFromDB.isPresent()) {
            save(file);
        } else {
            update(file);
        }
    }


    public FileDB save(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), getUsername());

        return fileDBRepository.save(FileDB);
    }

    public FileDB update(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileFromDB = fileDBRepository.findFirstByUsername(getUsername());
        fileFromDB.setName(fileName);
        fileFromDB.setType(file.getContentType());
        fileFromDB.setData(file.getBytes());
        return fileDBRepository.save(fileFromDB);
    }

    public FileDB getFile() {
        return fileDBRepository.findByUsername(getUsername()).get();
    }

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

}

