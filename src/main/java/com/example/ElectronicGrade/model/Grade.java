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
    private Double value;

    @Column(name = "waga")
    private Double weight;

    @Column(name = "komentarz")
    private String desc;
}
