package com.example.pokerplanninpi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idSession ;
    String Titre;
    LocalDateTime dateStart;
    LocalDateTime dateEnd;
    Float SessionNember;
    VarcharJdbcType Resultat;

    @ManyToOne
    @JsonIgnore
    Parametre parametre;

    @ManyToOne
    @JsonIgnore
    UserStory userStory;

    @ManyToOne
    @JsonIgnore
   Comentaire comentaire;

    @ManyToMany(mappedBy = "sessionList")
    @JsonIgnore
    List<User> userList;


    @ManyToOne
    @JsonIgnore
    Notification notification;
}
