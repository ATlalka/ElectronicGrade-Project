package com.example.ElectronicGrade.views.helloworld;

import com.example.ElectronicGrade.views.StudentMainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Arrays;
import java.util.List;

@PageTitle("Oceny")
@Route(value = "oceny", layout = StudentMainLayout.class)
@RouteAlias(value = "", layout = StudentMainLayout.class)
public class OcenyView extends HorizontalLayout {

    public OcenyView() {

        setMargin(true);
        //setVerticalComponentAlignment(Alignment.END, name, sayHello);

        //TODO Grid z defaulta jest podpiety pod liste czyli imo potrzebna metoda, ktora zwraca obiekt klasy Przedmiot
        // ktory zawiera nazwe z bazy i oceny podpiete pod dana nazwe
        List<Integer> oceny1 = Arrays.asList(3, 4, 4, 4, 4, 5, 4);
        List<Integer> oceny2 = Arrays.asList(5, 5, 3, 4, 4, 5, 4);
        List<Integer> oceny3 = Arrays.asList(4, 4, 4, 5, 4, 5, 2);

        List<Przedmiot> przedmioty = Arrays.asList(new Przedmiot("Matematyka", oceny1), new Przedmiot("Fizyka", oceny2), new Przedmiot("Informatyka", oceny3));
        Grid<Przedmiot> tabelaOcen = new Grid<>();
        tabelaOcen.setItems(przedmioty);

        tabelaOcen.addColumn(Przedmiot::getNazwa).setHeader("Nazwa Przedmiotu");
        tabelaOcen.addColumn(Przedmiot::getOceny).setHeader("Oceny");

        add(tabelaOcen);
    }

}