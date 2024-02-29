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
public class Comentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idComentaire ;
    String content;



    @OneToMany(mappedBy = "comentaire")
    @JsonIgnore
    List<Session> sessionList;

    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    }
