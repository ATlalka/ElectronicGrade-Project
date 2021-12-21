package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table (name = "Zajecia")
public class TimeTable {

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ZajeciaidZajecia", referencedColumnName = "idZajecia")
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        return lessons;
    }
}
