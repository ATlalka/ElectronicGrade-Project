package com.example.ElectronicGrade.views.studentGradeView;

import com.example.ElectronicGrade.model.entity.Grade;

import java.util.List;

public class Przedmiot {

    String Nazwa;
    List<Grade> Oceny;

    Przedmiot(String Nazwa, List<Grade> Oceny){
        this.Nazwa = Nazwa;
        this.Oceny = Oceny;
    }

    public String getNazwa(){
        return Nazwa;
    }

    public List<Grade> getOceny(){
        return Oceny;
    }
}
