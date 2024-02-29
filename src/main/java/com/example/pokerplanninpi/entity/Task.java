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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idTask ;
    String Name ;
    String Description;
    LocalDateTime DateCreat;

    @ManyToOne
    @JsonIgnore
    UserStory userStory;

    @OneToMany(mappedBy ="task")
    @JsonIgnore
    List<Projet> projetList;

    @ManyToOne
    @JsonIgnore
    User user;

}




