package com.example.ElectronicGrade.views.teacherGradeView;

import com.example.ElectronicGrade.model.entity.Class;
import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.entity.users.Teacher;
import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.model.service.StudentService;
import com.example.ElectronicGrade.model.service.TeacherService;
import com.example.ElectronicGrade.security.SecurityService;
import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;
import java.util.List;
import java.util.function.Consumer;

@PageTitle("Oceny")
@Route(value = "nauczycieloceny", layout = MainLayout.class)
public class TeacherGradesView extends VerticalLayout {

    private final Teacher teacher;
    private Subject chosenSubject;
    private Class chosenClass;
    //private Pair<Student, Grade> pair;
    private Grid<GradeData> grid = new Grid<>();

    @Autowired
    private final TeacherService teacherService;
    @Autowired
    private final StudentService studentService;

    ComboBox<Subject> subjectBox = new ComboBox<>("Przedmiot");
    ComboBox<com.example.ElectronicGrade.model.entity.Class>  classBox = new ComboBox<>("Klasa");
    Button subjectButton = new Button("Zmień przedmiot");
    Button addButton = new Button ("Dodaj ocenę");
    public TeacherGradesView(SecurityService securityService, @Autowired TeacherService teacherService, @Autowired StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.teacher = teacherService.findById(((User) securityService.getAuthenticatedUser()).getId()).orElseThrow();

        addButton.setEnabled(false);
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        setMargin(true);
        classBox.setEnabled(false);
        HorizontalLayout header = new HorizontalLayout();
        H1 viewTitle = new H1();
        header.add(viewTitle);
        header.setWidth("100%");
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");

        add(header);

        /*
        Combo Box lista przedmiotow
         */

        HorizontalLayout comboBoxesLayout =  new HorizontalLayout();
        VerticalLayout boxButtonLayout = new VerticalLayout();

        subjectButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        subjectButton.setEnabled(false);


        List <Subject> subjectList =  new ArrayList<>();

        Map<Subject, List<Class>> classMap = this.teacherService.findSubjectsByTeacherId(teacher.getId());

        for(Subject s : classMap.keySet()){

            subjectList.add(s);
        }

        subjectBox.setItems(subjectList);
        //subjectBox.setValue(subjectList.get(0));
        subjectBox.setAllowCustomValue(false);
        subjectBox.setItemLabelGenerator(Subject::getName);
        chosenSubject =  subjectList.get(0);
        subjectBox.addValueChangeListener(event -> subjectFunction(event.getValue()));



        comboBoxesLayout.add(subjectBox);


        /*
        Combo box lista klas
         */


        List<Class> classesList = new ArrayList<>();
        for(Subject s : classMap.keySet()){

            if(s.equals(chosenSubject))
            {
                classesList = classMap.get(s);
                break;
            }
        }

        classBox.setItems(classesList);
        //classBox.setValue(classesList.get(0));
        classBox.setAllowCustomValue(false);
        classBox.setItemLabelGenerator(com.example.ElectronicGrade.model.entity.Class::toString);
        chosenClass = classesList.get(0);
        classBox.addValueChangeListener(event -> classFunction(event.getValue()));

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        comboBoxesLayout.add(subjectBox,classBox);
        boxButtonLayout.add(comboBoxesLayout,subjectButton);
        add(boxButtonLayout,addButton,grid);
    }
    private void classFunction(com.example.ElectronicGrade.model.entity.Class Class){

        chosenClass = Class;
        addButton.setEnabled(true);
        Notification.show("Wybrano klase "+ chosenClass.toString());
        updateGrid();
    }

    private void updateGrid(){

        List<Student> students = teacherService.findStudentsByClassId(chosenClass.getId());
        grid.removeAllColumns();

        ArrayList<GradeData> dataList = new ArrayList<>();

        for(Student s : students){

            for(Grade g : s.getGrades(chosenSubject)){
                dataList.add(new GradeData(s,g, teacherService));
            }
        }

        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();

        Editor<GradeData> editor = grid.getEditor();

        Grid.Column<GradeData> nameColumn =  grid.addColumn(s -> s.getStudent().getFirstName());
        Grid.Column<GradeData> surnameColumn = grid.addColumn(s -> s.getStudent().getSurname());
        Grid.Column<GradeData> gradeColumn = grid.addColumn(s -> s.getGrade().getValue()).setHeader("Ocena");
        Grid.Column<GradeData> weightColumn = grid.addColumn(s -> s.getGrade().getWeight()).setHeader("Waga");
        Grid.Column<GradeData> dateColumn = grid.addColumn(s -> s.getGrade().getLesson().getDate()).setHeader("Data");
        Grid.Column<GradeData>  commentColumn = grid.addColumn(s -> s.getGrade().getDesc()).setHeader("Komentarz");

        Grid.Column<GradeData> editColumn = grid.addComponentColumn(gridDataItem -> {
            Button editButton = new Button ("Modyfikuj");
            //editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            editButton.addClickListener(e-> {
                if(editor.isOpen())
                    editor.cancel();

                grid.getEditor().editItem(gridDataItem);
            });
            return editButton;
        }).setWidth("150px").setFlexGrow(0);

        Grid.Column<GradeData> deleteColumn = grid.addComponentColumn(gridDataItem->{
            Button deleteButton = new Button ("Usuń");
            deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            deleteButton.addClickListener(e->{
                teacherService.deleteGrade(gridDataItem.getGrade());
                updateGrid();
                Notification.show("Usunieto ocene");
                //TODO Usuwanie
                //TODO popup z potwierdzeniem dokonania akcji

            });
            return deleteButton;
        }).setWidth("150px").setFlexGrow(0);

        ListDataProvider<GradeData> dataProvider = new ListDataProvider<>(dataList);
        grid.setDataProvider(dataProvider);

        StudentFilter studentFilter = new StudentFilter(dataProvider);

        headerRow.getCell(nameColumn).setComponent(createFilterHeader("Name",studentFilter::setName));
        headerRow.getCell(surnameColumn).setComponent(createFilterHeader("Surname",studentFilter::setSurname));


        Binder<GradeData> binder = new Binder<>(GradeData.class);
        editor.setBinder(binder);
        editor.setBuffered(true);

        TextField gradeValueField = new TextField();
        gradeValueField.setWidthFull();
        binder.forField(gradeValueField)
                .bind(GradeData::getGradeValue, GradeData::setGradeValue);
        gradeColumn.setEditorComponent(gradeValueField);

        TextField gradeWeightField = new TextField();
        gradeWeightField.setWidthFull();
        binder.forField(gradeWeightField)
                .bind(GradeData::getGradeWeight, GradeData::setGradeWeight);
        weightColumn.setEditorComponent(gradeWeightField);

        TextField gradeDescField = new TextField();
        gradeDescField.setWidthFull();
        binder.forField(gradeDescField)
                .bind(GradeData::getGradeDesc, GradeData::setGradeDesc);
        commentColumn.setEditorComponent(gradeDescField);

        Button saveButton = new Button("Save", e -> editor.save());
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
                e -> editor.cancel());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON,
                ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveButton,
                cancelButton);
        actions.setPadding(false);
        editColumn.setEditorComponent(actions);

    }

    private void subjectFunction (Subject subject){
            chosenSubject = subject;
            subjectButton.setEnabled(true);
            subjectBox.setEnabled(false);
            classBox.setEnabled(true);
    }

    private static Component createFilterHeader(String labelText,
                                                Consumer<String> filterChangeConsumer) {
        Label label = new Label(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }


}

//TODO dorobić wszystkie metody get i set i save które będą wykorzystywane w edytowaniu
class GradeData{
    private Student student;
    private Grade grade;
    private TeacherService teacherService;

    public GradeData(Student student, Grade grade, TeacherService teacherService) {
        this.student = student;
        this.grade = grade;
        this.teacherService = teacherService;
    }

    public Student getStudent() {
        return student;
    }

    public Grade getGrade() {
        return grade;
    }

    // waga wartosc i komentarz

    public String getGradeValue(){
        return Double.toString(grade.getValue());
    }

    public void setGradeValue(String val){
        double value = Double.parseDouble(val);
        if (value > 1.0 && value <= 6.0 && value % 0.5 == 0){
            grade.setValue(value);
            teacherService.saveGrade(grade);
            return;
        }

        //TODO info o zlym formacie
    }

    public String getGradeWeight(){
        return Double.toString(grade.getWeight());
    }

    public void setGradeWeight(String val){
        double value = Double.parseDouble(val);
        if (value > 1 && value <= 3 && value % 1 == 0){
            grade.setWeight(value);
            teacherService.saveGrade(grade);
            return;
        }
        //TODO info o zlym formacie
    }

    public String getGradeDesc(){
        return grade.getDesc();
    }

    public void setGradeDesc(String desc){
        grade.setDesc(desc);
        teacherService.saveGrade(grade);
        return;

    }
}

class StudentFilter{

    private final ListDataProvider<GradeData> dataProvider;

    private String fullName;
    private String surname;

    public StudentFilter(ListDataProvider<GradeData> dataProvider) {
        this.dataProvider = dataProvider;
        this.dataProvider.addFilter(this::test);
    }

    public void setName(String fullName) {
        this.fullName = fullName;
        this.dataProvider.refreshAll();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        this.dataProvider.refreshAll();
    }


    public boolean test(GradeData data) {
        boolean matchesFullName = matches(data.getStudent().getFirstName(), fullName);
        boolean matchesEmail = matches(data.getStudent().getSurname(), surname);

        return matchesFullName && matchesEmail;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty() || value
                .toLowerCase().contains(searchTerm.toLowerCase());
    }
}
