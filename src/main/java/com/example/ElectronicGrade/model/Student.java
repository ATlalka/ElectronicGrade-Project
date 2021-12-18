package com.example.ElectronicGrade.model;

import javax.persistence.*;

@Entity
@Table(name = "uczniowie")
@PrimaryKeyJoinColumn(name = "UzytkownicyidUzytkownik")
public class Student extends User {

}
