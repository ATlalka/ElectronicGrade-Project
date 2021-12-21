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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DaneLogowaniaidDaneLogowania", referencedColumnName = "idDaneLogowania")
    private LoginData loginData;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OsobyidOsoba", referencedColumnName = "idOsoba")
    private Person person;

}
