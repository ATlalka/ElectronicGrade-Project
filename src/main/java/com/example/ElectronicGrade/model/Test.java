package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "sprawdziany")
public class Test {

    @Id
    @GeneratedValue
    @Column (name = "idSprawdzian")
    private Long id;

    @Column(name = "Komentarz")
    private String desc;
}
