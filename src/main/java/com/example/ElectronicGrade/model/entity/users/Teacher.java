package com.example.ElectronicGrade.model.entity.users;

import com.example.ElectronicGrade.model.entity.Class;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Nauczyciele")
public class Teacher extends User {

    @Column(name="TytulNaukowy")
    private String degree;

    @Override
    public Collection<Role> getRoles() {
        return List.of(Role.TEACHER);
    }

}
