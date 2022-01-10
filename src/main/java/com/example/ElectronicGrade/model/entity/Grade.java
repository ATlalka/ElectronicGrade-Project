package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;

@Entity
@Table(name= "Oceny")
public class Grade {

    public Grade(Double value, Double weight, String desc, Lesson lesson) {
        this.value = value;
        this.weight = weight;
        this.desc = desc;
        this.lesson = lesson;
    }

    public Grade() {}

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
