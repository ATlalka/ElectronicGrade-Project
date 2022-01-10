package com.example.ElectronicGrade.views.studentGradeView;

import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.security.SecurityService;
import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.*;

@PageTitle("Oceny")
@Route(value = "oceny", layout = MainLayout.class)
public class OcenyView extends HorizontalLayout {

    private final Student student;

    public OcenyView(SecurityService securityService) {
        this.student = (Student) securityService.getAuthenticatedUser();
        setMargin(true);

        HorizontalLayout header = new HorizontalLayout();
        H1 viewTitle = new H1();
        header.add(viewTitle);
        header.setWidth("100%");
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");

        VerticalLayout verticalLayout =  new VerticalLayout();
        verticalLayout.add(header);

        add(verticalLayout);

        Map<Subject, List<Grade>> gradesMap = student.getGradesMap();

        for(Subject s : gradesMap.keySet()){
            ArrayList<Grade> grades = (ArrayList<Grade>) gradesMap.get(s);
            String subjectName = s.getName();

            Label l = new Label(subjectName);

            Grid<Grade> gradeGrid = new Grid<>();
            gradeGrid.setItems(grades);
            gradeGrid.addColumn(c->c.getValue()).setHeader("Ocena").setAutoWidth(true);
            gradeGrid.addColumn(c->c.getWeight().toString()).setHeader("Waga");
            gradeGrid.addColumn(c->c.getDesc()).setHeader("Komentarz").setAutoWidth(true);
            gradeGrid.addColumn(c->c.getLesson().getDate().toString()).setHeader("Data");

            verticalLayout.add(l);
            verticalLayout.add(gradeGrid);
        }
    }
}
