package com.example.pokerplanninpi.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class File {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @ManyToOne
    Projet projet;

    public File(String name, String type, byte[] data, Projet projet) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.projet = projet;
    }
}

