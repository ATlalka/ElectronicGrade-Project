package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "lekcje")
public class Lesson {

    @Id
    @GeneratedValue
    @Column (name = "idLekcja")
    private Long id;

    @Column (name = "temat")
    private String topic;

    public Date getDate() {
        return date;
    }

    @Column (name = "data")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "zajeciaidZajecia", referencedColumnName = "idZajecia")
    private Course course;

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Temat: '" + topic + '\'' +
                ", data: " + date;
    }
}
