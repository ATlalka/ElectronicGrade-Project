package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table (name = "Zajecia")
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "idZajecia")
    private Long id;

    @Column(name = "dzienTygodnia")
    private int dayOfTheWeek;

    @Column (name = "godzina")
    private Timestamp time;

    @Column (name = "zarchiwizowana")
    private boolean ifArchived;

    @ManyToOne
    @JoinColumn(name = "przedmiotyidPrzedmiot", referencedColumnName = "idPrzedmiot")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }
}
