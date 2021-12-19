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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LekcjeidLekcja", referencedColumnName = "idLekcja")
    private List<Object> grades;

    public List<Object> getGrades() {
        return grades;
    }

    // TO DO: co z kluczem obcym w tej kolumnie
    // TO DO: dopisać listę ocen (i obecności)
}
