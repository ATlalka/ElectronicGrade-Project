package com.example.ElectronicGrade.views.teacherOcenyView;

import com.example.ElectronicGrade.views.teacherOcenyView.Uczen;

import java.util.List;

public class Klasa {

    private String symbol;
    private List<Uczen> uczniowie;

    Klasa(String symbol, List<Uczen> uczniowie){
        this.symbol=symbol;
        this.uczniowie =uczniowie;
    }

    public String getSymbol(){
        return symbol;
    }
    public List<Uczen> getUczniowie(){
        return uczniowie;
    }
}
