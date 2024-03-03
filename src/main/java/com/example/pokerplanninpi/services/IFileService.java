package com.example.pokerplanninpi.services;


import com.example.pokerplanninpi.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface IFileService {

    File storeProjetFile(Integer idprojet, MultipartFile file) throws IOException;
    Stream<File> getFilesByProjet(Integer idprojet);
}
