package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "DaneLogowania")
public class LoginData {

    @Id
    @GeneratedValue
    @Column(name = "idDaneLogowania")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "haslo", nullable = false, length = 64)
    private String password;

}
