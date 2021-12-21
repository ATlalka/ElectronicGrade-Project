package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Osoby")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "idOsoba")
    private Long id;

    @Column(name = "imie")
    private String name;

    @Column(name = "nazwisko")
    private String surname;

    @Column(name = "pesel")
    private Long code;

    @Column(name = "dataUrodzenia")
    private Date birthdayDate;
    
}
