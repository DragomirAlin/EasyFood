package com.easyfood.files.controller;


import com.easyfood.files.payload.ResponseMessage;
import com.easyfood.files.persistence.FileDB;
import com.easyfood.files.service.FileStorageService;
import com.easyfood.files.service.FileStorageServiceImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/storage")
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

//    @GetMapping("/files")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }

    @GetMapping("/files/download")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/files/view")
    public FileDB getImgUtility() throws UnsupportedEncodingException {
        FileDB fileDB = storageService.getFile();

        byte[] encodeBase64 = Base64.encodeBase64(fileDB.getData());
//        String base64Encoded = new String(encodeBase64, "UTF-8");
//        return base64Encoded;
        return fileDB;

    }
}