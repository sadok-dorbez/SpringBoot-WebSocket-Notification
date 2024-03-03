package com.example.pokerplanninpi.controllers;
import com.example.pokerplanninpi.dto.ResponseFile;
import com.example.pokerplanninpi.dto.ResponseMessage;
import com.example.pokerplanninpi.entity.Projet;
import com.example.pokerplanninpi.services.ProjetService;
import com.example.pokerplanninpi.services.IFileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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

}
