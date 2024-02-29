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
public class Reclamtion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long Reclamation ;
    String name;
    String descriptionReclamation;
    LocalDateTime dateSoumission;
    long priorite ;

    @Enumerated(EnumType.STRING)
    statusReclamation status;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore

    List<Projet> projetList;
    @ManyToOne
    @JsonIgnore

    User user;


}
