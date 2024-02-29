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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long id ;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;



    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Reclamtion> reclamtionList;

    @ManyToOne
    @JsonIgnore
    UserStory userStory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore

    List<Session> sessionList;


    @ManyToOne
    @JsonIgnore
    Notification  notification;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Task> taskList;



}
