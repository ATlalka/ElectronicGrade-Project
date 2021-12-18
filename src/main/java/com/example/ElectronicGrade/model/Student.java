package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "uczniowie")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "idUczen")
    private Long id;


}
