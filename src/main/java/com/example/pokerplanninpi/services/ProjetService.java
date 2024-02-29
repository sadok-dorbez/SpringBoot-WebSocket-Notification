package com.example.pokerplanninpi.services;
import com.example.pokerplanninpi.entity.Projet;

import java.util.List;
public interface ProjetService {






        List<Projet> getAllProjets();
        Projet getProjetById(Integer id);
        Projet createProjet(Projet projet);
        Projet updateProjet(Integer id, Projet projet);
        void deleteProjet(Integer id);


}
