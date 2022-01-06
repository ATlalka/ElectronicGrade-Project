package com.example.ElectronicGrade.model.entity.users;

import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Class;
import com.example.ElectronicGrade.model.entity.Subject;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Uczniowie")
public class Student extends User {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "UczniowieidUczen", referencedColumnName = "idUzytkownik")
    private List<Grade> grades;

    @ManyToOne
    @JoinColumn(name = "KlasyidKlasa", referencedColumnName = "idKlasa")
    private Class studentClass;

    public Student() {
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public Collection<Role> getRoles() {
        return List.of(Role.STUDENT);
    }

    @OneToMany(fetch = FetchType.LAZY)
    public Class getStudentClass() {
        return studentClass;
    }
}
