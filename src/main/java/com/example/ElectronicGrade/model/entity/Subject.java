package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Przedmioty")
public class Subject {

    @Id
    @GeneratedValue
    @Column (name = "idPrzedmiot")
    private Long id;

    @Column (name = "nazwa")
    private String name;

    public String getName() {
        return name;
    }

    @OneToMany
    @JoinColumn(name = "PrzedmiotyidPrzedmiot", referencedColumnName = "idPrzedmiot")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }
}
