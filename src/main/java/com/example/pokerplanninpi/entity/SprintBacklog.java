package com.example.pokerplanninpi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SprintBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idSprintBacklog;
    String nomTache;
    String description;
    int priorite;
    String estimation;
    String responsable;
    status etat ;
    String comentaire;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    String elementsLiees;
    @OneToMany(mappedBy = "sprintBacklog")
    @JsonIgnore
    List<ProductBacklog>  productBacklogList;

    @OneToMany(mappedBy = "sprintBacklog")
    @JsonIgnore
    List<UserStory>  userStoryList;


}
