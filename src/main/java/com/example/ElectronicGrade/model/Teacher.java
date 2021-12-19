package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "Nauczyciele")
@PrimaryKeyJoinColumn(name = "UzytkownicyidUzytkownik")
public class Teacher extends User {

    @Column(name="TytulNaukowy")
    private String degree;

    @Column (name = "CzyWychowawca")
    private boolean ifEducator;

    @OneToOne
    @JoinColumn (name = "KlasaidKlasy", referencedColumnName = "idKlasy")
    private int classId;


}
