package com.example.pokerplanninpi.controllers;
import com.example.pokerplanninpi.dto.ResponseFile;
import com.example.pokerplanninpi.dto.ResponseMessage;
import com.example.pokerplanninpi.entity.Image;
import com.example.pokerplanninpi.entity.Projet;
import com.example.pokerplanninpi.services.ProjetService;
import com.example.pokerplanninpi.services.IFileService;
import com.example.pokerplanninpi.services.IImageService;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("**")
@RestController
@RequestMapping(value="/api/projet")
public class ProjetController {

        @Autowired
        private ProjetService projetServices;
        @Autowired
        private IFileService fileService;
        @Autowired
        private IImageService imageService;
        @GetMapping
        public List<Projet> getAllprojets() {
            return projetServices.getAllProjets();
        }

        @GetMapping("/{id}")
        public Projet getProjetById(@PathVariable Integer id) {
            return projetServices.getProjetById(id);
        }

        @PostMapping
        public Projet createprojet(@RequestBody Projet projet) {
            return projetServices.createProjet(projet);
        }

        @PutMapping("/{id}")
        public Projet updateProjet(@PathVariable Integer id, @RequestBody Projet projet) {
            return projetServices.updateProjet(id, projet);
        }

        @DeleteMapping("/{id}")
        public void deleteProjet(@PathVariable Integer id) {
            projetServices.deleteProjet(id);
        }

    @PostMapping("/{idprojet}/uploadimage")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int idprojet) {
        String message = "";
        try {
            fileService.storeProjetFile(idprojet, file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/{idprojet}/images")
    public ResponseEntity<List<ResponseFile>> getListFiles(@PathVariable int idprojet) {
        List<ResponseFile> files = fileService.getFilesByProjet(idprojet).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            imageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Image>> getListFiles() {
        List<Image> imageInfos = imageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ProjetController.class, "getFile", path.getFileName().toString()).build().toString();
            System.out.println(filename);
            System.out.println(url);
            return new Image(filename, url);
        }).collect(Collectors.toList());
        System.out.println(imageInfos);
        return ResponseEntity.status(HttpStatus.OK).body(imageInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource image = imageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"").body(image);
    }
}
