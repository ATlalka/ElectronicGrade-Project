package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "uzytkownicy")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    @Column(name = "idUzytkownik")
    private Long id;

    @Column(name = "email")
    private String email;

}
