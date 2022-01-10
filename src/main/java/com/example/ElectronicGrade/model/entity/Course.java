package com.example.ElectronicGrade.model.entity;

import com.example.ElectronicGrade.model.entity.users.Teacher;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @ManyToOne
    @JoinColumn(name = "przedmiotyidPrzedmiot", referencedColumnName = "idPrzedmiot")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "nauczycieleidNauczyciel", referencedColumnName = "idUzytkownik")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "klasyidKlasa", referencedColumnName = "idKlasa")
    private Class courseClass;

    public Subject getSubject() {
        return subject;
    }

    public Class getCourseClass() {
        return courseClass;
    }

    public String toString() {
        return subject.getName() + " " + courseClass.toString();
    }
}
