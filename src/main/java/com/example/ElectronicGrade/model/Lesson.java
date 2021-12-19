package com.example.ElectronicGrade.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "lekcje")
public class Lesson {

    @Id
    @GeneratedValue
    @Column (name = "idLekcja")
    private Long id;

    @Column (name = "temat")
    private String topic;

    @Column (name = "data")
    private Date date;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "LekcjeidLekcja", referencedColumnName = "idLekcja")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

}
