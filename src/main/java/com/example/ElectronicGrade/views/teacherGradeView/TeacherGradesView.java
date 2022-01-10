package com.example.ElectronicGrade.views.teacherGradeView;

import com.example.ElectronicGrade.model.entity.users.Teacher;
import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.model.service.TeacherService;
import com.example.ElectronicGrade.security.SecurityService;
import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@PageTitle("Oceny")
@Route(value = "nauczycieloceny", layout = MainLayout.class)
public class TeacherGradesView extends VerticalLayout {

    private final Teacher teacher;

    @Autowired
    private final TeacherService teacherService;

    private final Grid <Student> klasaGrid = new Grid<>();
    List<Class> klasy = new ArrayList<>();

    public TeacherGradesView(SecurityService securityService, @Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
        this.teacher = teacherService.findById(((User) securityService.getAuthenticatedUser()).getId()).orElseThrow();

        setMargin(true);
        List<Integer> oceny1 = Arrays.asList(5, 4, 5, 4, 4, 5, 4);
        List<Integer> oceny2 = Arrays.asList(5, 5, 3, 6, 4, 5, 4);
        List<Integer> oceny3 = Arrays.asList(4, 5, 5, 5, 6, 5, 4);

        Student student1 = new Student("Natalia", "Rusin", oceny1);
        Student student2 = new Student("Aleksandra", "Tlałka", oceny2);
        Student student3 = new Student("Mikołaj", "Chmielecki", oceny3);

        List<Student> uczniowie1A = new ArrayList<>();
        List<Student> uczniowie2A = new ArrayList<>();
        uczniowie1A.add(student1);
        uczniowie1A.add(student2);
        uczniowie2A.add(student3);

        uczniowie1A.sort(Comparator.comparing(Student::getNazwisko));
        uczniowie2A.sort(Comparator.comparing(Student::getNazwisko));

        //TODO przy tworzeniu klasy rok i symbol trzeba laczyc w jednego stringa robimy liste klas, ktore spelniaja warunki z ComboBoxa
        //TODO lista klasy zawiera te klasy, ktorych uczy dany nauczyciel (symbole)
        //TODO lista przedmioty zawiera te przedmioty, ktorych nauczyciel uczy klase wybrana z Combo Boxa
        //TODO rekordy - zawieraja uczniow danej klasy i ich oceny z danego przedmiotu
        Class class1A = new Class("1A", uczniowie1A);
        Class class2A = new Class("2A", uczniowie2A);


        klasy.add(class2A);
        klasy.add(class1A);
        klasy.sort(Comparator.comparing(Class::getSymbol));

        klasaGrid.setItems(klasy.get(0).getUczniowie());
        function(klasy.get(0));
        ComboBox<Class> classBox = new ComboBox<>("Klasa");
        classBox.setItems(klasy);
        classBox.setValue(klasy.get(0));
        classBox.setAllowCustomValue(false);
        classBox.setItemLabelGenerator(Class::getSymbol);
        classBox.addValueChangeListener(event -> function(event.getValue()));

        add(classBox);

        add(klasaGrid);


    }
    public void function(Class aClass){
        klasaGrid.removeAllColumns();
        klasaGrid.setItems(aClass.getUczniowie());
        klasaGrid.addColumn(Student::getImie).setHeader("Imie");
        klasaGrid.addColumn(Student::getNazwisko).setHeader("Nazwisko");
        klasaGrid.addColumn(Student::getOceny).setHeader("Oceny");
    }


}
