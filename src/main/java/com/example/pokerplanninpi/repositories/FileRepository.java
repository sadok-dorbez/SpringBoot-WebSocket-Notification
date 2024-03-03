package com.example.pokerplanninpi.repositories;


import com.example.pokerplanninpi.entity.File;
import com.example.pokerplanninpi.entity.Projet;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    File findByName(String name);
    List<File> findByProjet(Projet projet);
}