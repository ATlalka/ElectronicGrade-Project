package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;

@Entity
@Table (name = "Przedmioty")
public class Subject {

    @Id
    @GeneratedValue
    @Column (name = "idPrzedmiot")
    private Long id;

    @Column (name = "nazwa")
    private String name;

    // TO DO: przedmioty z zajęciami
}
