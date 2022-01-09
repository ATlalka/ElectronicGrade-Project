package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;

@Entity
@Table(name= "Oceny")
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "idOcena")
    private Long id;

    @Column (name = "wartosc")
    private Double value;

    @Column(name = "waga")
    private Double weight;

    @Column(name = "komentarz")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "lekcjeidLekcja", referencedColumnName = "idLekcja")
    private Lesson lesson;

    public Lesson getLesson() {
        return lesson;
    }

    public Subject getSubject() {
        return getLesson().getCourse().getSubject();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
