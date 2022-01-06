package com.example.ElectronicGrade.views.teacherGradeView;

import java.util.List;

public class Student {

    private String imie;
    private String nazwisko;
    private List<Integer> oceny;

    Student(String imie, String nazwisko, List <Integer> oceny){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this. oceny = oceny;

    }

    public String getImie(){
        return imie;
    }
    public String getNazwisko(){
        return nazwisko;
    }

    public List<Integer> getOceny(){
        return oceny;
    }

}
