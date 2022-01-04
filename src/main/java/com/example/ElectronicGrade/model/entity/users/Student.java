package com.example.ElectronicGrade.model.entity.users;

import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Uczniowie")
public class Student extends User {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "UczniowieidUczen", referencedColumnName = "idUzytkownik")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
