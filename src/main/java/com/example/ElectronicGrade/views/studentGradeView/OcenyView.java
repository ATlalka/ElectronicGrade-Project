package com.example.ElectronicGrade.views.studentGradeView;

import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.security.SecurityService;
import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@PageTitle("Oceny")
@Route(value = "oceny", layout = MainLayout.class)
public class OcenyView extends HorizontalLayout {

    private final Student student;

    public OcenyView(SecurityService securityService) {
        this.student = (Student) securityService.getAuthenticatedUser();
        setMargin(true);

        Map<Subject, List<Grade>> gradesMap = student.getGradesMap();

        //TODO Grid z defaulta jest podpiety pod liste czyli imo potrzebna metoda, ktora zwraca obiekt klasy Przedmiot
        // ktory zawiera nazwe z bazy i oceny podpiete pod dana nazwe
        List<Integer> oceny1 = Arrays.asList(3, 4, 4, 4, 4, 5, 4);
        List<Integer> oceny2 = Arrays.asList(5, 5, 3, 4, 4, 5, 4);
        List<Integer> oceny3 = Arrays.asList(4, 4, 4, 5, 4, 5, 2);

        List<Przedmiot> przedmioty = Arrays.asList(new Przedmiot("Matematyka", oceny1), new Przedmiot("Fizyka", oceny2), new Przedmiot("Informatyka", oceny3));
        Grid<Map.Entry<Subject, List<Grade>>> tabelaOcen = new Grid<>();
        tabelaOcen.setItems(gradesMap.entrySet());

        tabelaOcen.addColumn(c -> c.getKey().getName()).setHeader("Nazwa Przedmiotu");
        tabelaOcen.addColumn(c -> c.getValue().toString()).setHeader("Oceny");

        add(tabelaOcen);
    }

}