package com.example.ElectronicGrade.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "child_class")
public class ChildClass extends ParentClass {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_child_class", referencedColumnName = "id")
    private List<Grade> grades;
}
