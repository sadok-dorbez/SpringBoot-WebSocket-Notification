package com.example.pokerplanninpi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idProjet ;
    String nom;
    String description;
    Date dateDebut;
    Date dateFin;


    @ManyToMany(mappedBy = "projetList")
    @JsonIgnore
    List<Reclamtion> reclamtionList;

    @ManyToOne
    @JsonIgnore
    Task task;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projet")
    private Set<Image> images;
    @ManyToOne
    @JsonIgnore
    ProductBacklog productBacklog;

}

