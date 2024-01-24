package com.files.upload.db.springbootuploadfilesdatabase.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.files.upload.db.springbootuploadfilesdatabase.model.FileDB;
import com.files.upload.db.springbootuploadfilesdatabase.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public List<FileDB> storeMultiple(List<MultipartFile> files) {
        List<FileDB> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                FileDB uploadedFile = store(file);
                uploadedFiles.add(uploadedFile);
            } catch (IOException e) {
                // Handle the IOException as needed (log, throw a custom exception, etc.)
                e.printStackTrace(); // Example: print the stack trace
            }
        }

        return uploadedFiles;
    }
}
