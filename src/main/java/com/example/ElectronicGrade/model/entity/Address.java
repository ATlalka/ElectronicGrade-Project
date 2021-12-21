package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "adresy")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "idAdres")
    private Long id;

    @Column(name = "ulica")
    private String street;

    @Column(name = "numerDomu")
    private Integer number;

    @Column(name = "numerMieszkania")
    private Integer flatNumber;

    @Column(name = "miejscowosc")
    private String city;

    @Column(name = "kodPocztowy")
    private String postalCode;
}
