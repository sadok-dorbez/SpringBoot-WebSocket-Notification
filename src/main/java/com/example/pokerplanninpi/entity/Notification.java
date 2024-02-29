package com.example.pokerplanninpi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idNotification ;
    String contenu;
    Date dateEnvoi;


    @OneToMany(mappedBy = "notification")
    @JsonIgnore
    List<User> userList;

    @OneToMany(mappedBy = "notification")
    @JsonIgnore
    List<Session> sessionList;

}
