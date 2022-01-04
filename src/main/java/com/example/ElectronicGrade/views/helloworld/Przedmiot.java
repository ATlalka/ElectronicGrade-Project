package com.example.ElectronicGrade.views.helloworld;

import java.util.List;

public class Przedmiot {

    String Nazwa;
    List<Integer> Oceny;

    Przedmiot(String Nazwa, List<Integer> Oceny){
        this.Nazwa = Nazwa;
        this.Oceny = Oceny;
    }

    public String getNazwa(){
        return Nazwa;
    }

    public List<Integer> getOceny(){
        return Oceny;
    }
}
