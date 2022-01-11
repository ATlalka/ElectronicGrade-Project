package com.example.ElectronicGrade.model.entity.users;

import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Class;
import com.example.ElectronicGrade.model.entity.Subject;

import javax.persistence.*;
import java.util.*;

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

    public Map<Subject, List<Grade>> getGradesMap() {
        Map<Subject, List<Grade>> result = new LinkedHashMap<>();
        for (Grade grade : grades) {
            if (!result.containsKey(grade.getSubject())) {
                result.put(grade.getSubject(), new ArrayList<>());
            }
            result.get(grade.getSubject()).add(grade);
        }
        return result;
    }

    public List<Grade> getGrades(Subject subject) {
        List<Grade> result = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getSubject().getId() == subject.getId()) {
                result.add(grade);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return getSurname() + " " + getFirstName();
    }
}
