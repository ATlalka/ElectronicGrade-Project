package com.example.ElectronicGrade.model.entity;

import com.example.ElectronicGrade.model.entity.users.Student;

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

    @ManyToMany
    @JoinTable(
            name = "Rozszerzenia",
            joinColumns = { @JoinColumn(name = "KlasyidKlasa", referencedColumnName = "idKlasa") },
            inverseJoinColumns = { @JoinColumn(name = "PrzedmiotyidPrzedmiot", referencedColumnName = "idPrzedmiot") }
    )
    private List<Subject> extensions;

    public List<Student> getStudents() {
        return students;
    }

    public List<Subject> getExtensions() {
        return extensions;
    }

    @Override
    public String toString() {
        return grade.toString() + symbol;
    }

    public String getExtensionsNames() {
        String result = "Rozszerzenia: ";
        for (Subject subject : extensions) {
            result += subject.getName() + ", ";
        }
        return result;
    }

    public Long getId() {
        return id;
    }
}
