package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "DaneLogowania")
public class LoginData {

    @Id
    @GeneratedValue
    @Column(name = "idDaneLogowania")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "haslo")
    private String haslo;

}
