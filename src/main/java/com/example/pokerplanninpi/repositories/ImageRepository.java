package com.example.pokerplanninpi.repositories;

import com.example.pokerplanninpi.entity.Image;
import com.example.pokerplanninpi.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByName(String name);
    List<Image> findByProjet(Projet projet);
}