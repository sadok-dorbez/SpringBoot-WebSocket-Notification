package com.example.pokerplanninpi.services;


import com.example.pokerplanninpi.entity.File;
import com.example.pokerplanninpi.entity.Projet;
import com.example.pokerplanninpi.repositories.FileRepository;
import com.example.pokerplanninpi.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileService implements IFileService{

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @Override
    public File storeProjetFile(Integer idprojet, MultipartFile file) throws IOException {
        Projet projet = projetRepository.findById(idprojet).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes(), projet);

        return fileRepository.save(FileDB);
    }


    @Override
    public Stream<File> getFilesByProjet(Integer idprojet)
    {
        Projet projet = projetRepository.findById(idprojet).get();
        return fileRepository.findByProjet(projet).stream();
    }


}