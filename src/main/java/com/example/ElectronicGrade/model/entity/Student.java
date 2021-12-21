package com.example.ElectronicGrade.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Uczniowie")
public class Student extends User {

    private static final String ROLE = "TEACHER";

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "UczniowieidUczen", referencedColumnName = "idUzytkownik")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String getRole() {
        return ROLE;
    }
}
