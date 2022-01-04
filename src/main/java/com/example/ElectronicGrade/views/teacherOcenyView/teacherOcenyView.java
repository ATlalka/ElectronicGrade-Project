package com.example.ElectronicGrade.views.teacherOcenyView;

import com.example.ElectronicGrade.views.StudentMainLayout;
//import com.example.ElectronicGrade.views.TeacherMainLayout;
import com.example.ElectronicGrade.views.TeacherMainLayout;
import com.example.ElectronicGrade.views.helloworld.Przedmiot;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.aspectj.weaver.ast.Not;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@PageTitle("Oceny")
@Route(value = "nauczycieloceny", layout = TeacherMainLayout.class)
//@RouteAlias(value = "", layout =  TeacherMainLayout.class)
public class teacherOcenyView  extends VerticalLayout {

    private final Grid <Uczen> klasaGrid = new Grid<>();
    List<Klasa> klasy = new ArrayList<>();
    public teacherOcenyView() {

        setMargin(true);
        List<Integer> oceny1 = Arrays.asList(5, 4, 5, 4, 4, 5, 4);
        List<Integer> oceny2 = Arrays.asList(5, 5, 3, 6, 4, 5, 4);
        List<Integer> oceny3 = Arrays.asList(4, 5, 5, 5, 6, 5, 4);

        Uczen uczen1 = new Uczen("Natalia", "Rusin", oceny1);
        Uczen uczen2 = new Uczen("Aleksandra", "Tlałka", oceny2);
        Uczen uczen3 = new Uczen("Mikołaj", "Chmielecki", oceny3);

        List<Uczen> uczniowie1A = new ArrayList<>();
        List<Uczen> uczniowie2A = new ArrayList<>();
        uczniowie1A.add(uczen1);
        uczniowie1A.add(uczen2);
        uczniowie2A.add(uczen3);

        uczniowie1A.sort(Comparator.comparing(Uczen::getNazwisko));
        uczniowie2A.sort(Comparator.comparing(Uczen::getNazwisko));

        //TODO przy tworzeniu klasy rok i symbol trzeba laczyc w jednego stringa robimy liste klas, ktore spelniaja warunki z ComboBoxa
        Klasa klasa1A = new Klasa("1A", uczniowie1A);
        Klasa klasa2A = new Klasa("2A", uczniowie2A);


        klasy.add(klasa2A);
        klasy.add(klasa1A);
        klasy.sort(Comparator.comparing(Klasa::getSymbol));

        klasaGrid.setItems(klasy.get(0).getUczniowie());
        function(klasy.get(0));
        ComboBox<Klasa> classBox = new ComboBox<>("Klasa");
        classBox.setItems(klasy);
        classBox.setValue(klasy.get(0));
        classBox.setAllowCustomValue(false);
        classBox.setItemLabelGenerator(Klasa::getSymbol);
        classBox.addValueChangeListener(event -> function(event.getValue()));

        add(classBox);

        add(klasaGrid);


    }
    public void function(Klasa klasa){
        klasaGrid.removeAllColumns();
        klasaGrid.setItems(klasa.getUczniowie());
        klasaGrid.addColumn(Uczen::getImie).setHeader("Imie");
        klasaGrid.addColumn(Uczen::getNazwisko).setHeader("Nazwisko");
        klasaGrid.addColumn(Uczen::getOceny).setHeader("Oceny");
    }


}
