package com.example.ElectronicGrade.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uczniowie")
@PrimaryKeyJoinColumn(name = "UzytkownicyidUzytkownik")
public class Student extends User {

}
