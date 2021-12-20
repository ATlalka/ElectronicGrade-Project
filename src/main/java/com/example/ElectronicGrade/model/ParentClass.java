package com.example.ElectronicGrade.model;


import javax.persistence.*;

@Entity
@Table(name = "parent_class")
@Inheritance(strategy = InheritanceType.JOINED)
public class ParentClass {

    @Id
    @GeneratedValue
    private Long id;

}
