package com.example.pokerplanninpi.controllers;
import com.example.pokerplanninpi.entity.Projet;
import com.example.pokerplanninpi.services.ProjetServiceImp;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@CrossOrigin("**")
@RestController
@RequestMapping(value="/api/projet")
public class ProjetController {

        @Autowired
        private ProjetServiceImp projetServices;
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


}
