package com.example.ElectronicGrade.model.entity.users;

import com.example.ElectronicGrade.model.entity.Class;
import com.example.ElectronicGrade.model.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "Nauczyciele")
public class Teacher extends User {

    @Column(name="TytulNaukowy")
    private String degree;

    @Column (name = "CzyWychowawca")
    private boolean ifEducator;

    @OneToOne
    @JoinColumn (name = "KlasyidKlasa", referencedColumnName = "idKlasa")
    private Class onesClass;

    @Override
    public Role getRole() {
        return Role.TEACHER;
    }

}
