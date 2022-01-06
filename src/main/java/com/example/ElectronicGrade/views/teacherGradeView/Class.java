package com.example.ElectronicGrade.views.teacherGradeView;

import java.util.List;

public class Class {

    private String symbol;
    private List<Student> uczniowie;

    Class(String symbol, List<Student> uczniowie){
        this.symbol=symbol;
        this.uczniowie =uczniowie;
    }

    public String getSymbol(){
        return symbol;
    }
    public List<Student> getUczniowie(){
        return uczniowie;
    }
}
