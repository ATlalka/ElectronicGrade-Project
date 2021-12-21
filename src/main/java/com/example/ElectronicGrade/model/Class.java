package com.example.ElectronicGrade.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "klasy")
public class Class {

    @Id
    @GeneratedValue
    @Column(name = "idKlasa")
    private Long id;

    @Column(name = "rok")
    private Integer grade;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "rocznik")
    private Integer year;

    @OneToMany
    @JoinColumn(name = "KlasyidKlasa", referencedColumnName = "idKlasa")
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }
}
