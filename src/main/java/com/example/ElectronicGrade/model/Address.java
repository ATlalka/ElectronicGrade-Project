package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "Adresy")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "idAdres")
    private Long id;

    @Column(name = "ulica")
    private String street;

}
