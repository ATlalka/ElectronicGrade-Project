package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "Nauczyciele")
public class Teacher extends User {

    private static final String ROLE = "TEACHER";

    @Column(name="TytulNaukowy")
    private String degree;

    @Column (name = "CzyWychowawca")
    private boolean ifEducator;

    @OneToOne
    @JoinColumn (name = "KlasyidKlasa", referencedColumnName = "idKlasa")
    private Class onesClass;

    @Override
    public String getRole() {
        return ROLE;
    }

}
