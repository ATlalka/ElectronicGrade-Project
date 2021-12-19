package com.example.ElectronicGrade.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uczniowie")
@PrimaryKeyJoinColumn(name = "UzytkownicyidUzytkownik")
public class Student extends User {

    @Id
    @GeneratedValue
    @Column(name = "idUczen")
    private Long idStudent;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "UczniowieidUczen", referencedColumnName = "idUczen")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }


}
