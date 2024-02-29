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
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idUserStory ;
    String title;
    String Description;
    LocalDateTime StartDate;
    LocalDateTime endDate;
    String assignee;

    @Enumerated(EnumType.STRING)
    Verifier verifier;
    @Enumerated(EnumType.STRING)
    status status;



    @OneToMany(mappedBy = "userStory")
    @JsonIgnore
    List<Session> sessionList;
    @OneToMany(mappedBy = "userStory")
    @JsonIgnore
    List<Task> taskList;

    @ManyToOne
    @JsonIgnore

    SprintBacklog sprintBacklog;

    @OneToMany(mappedBy = "userStory")
    @JsonIgnore
    List<User> userList;


}
