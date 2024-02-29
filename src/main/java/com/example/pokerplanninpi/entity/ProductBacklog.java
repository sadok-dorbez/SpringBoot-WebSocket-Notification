package com.example.pokerplanninpi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBacklog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idProductBacklog ;
    String Priority;
    String Functionality;

    @OneToMany(mappedBy = "productBacklog")
    @JsonIgnore
    List<Projet> projetList;


    @ManyToOne
    @JsonIgnore
    SprintBacklog sprintBacklog;



}
