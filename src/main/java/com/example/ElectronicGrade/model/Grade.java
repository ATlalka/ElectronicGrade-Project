package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name= "Oceny")
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "idOcena")
    private Long id;

    @Column (name = "wartosc")
    private double value;

    @Column(name = "waga")
    private double weight;

    @Column(name = "komentarz")
    private String desc;
}
