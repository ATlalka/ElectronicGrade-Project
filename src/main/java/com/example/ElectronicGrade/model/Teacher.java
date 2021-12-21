package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "Nauczyciele")
public class Teacher extends User {

    @Column(name="TytulNaukowy")
    private String degree;

    @Column (name = "CzyWychowawca")
    private boolean ifEducator;

    @OneToOne
    @JoinColumn (name = "KlasyidKlasa", referencedColumnName = "idKlasa")
    private Class onesClass;

}
