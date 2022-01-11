package com.example.ElectronicGrade.views.teacherGradeView;

import com.example.ElectronicGrade.model.entity.Class;
import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Teacher;
import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.model.service.StudentService;
import com.example.ElectronicGrade.model.service.TeacherService;
import com.example.ElectronicGrade.security.SecurityService;
import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

@PageTitle("Oceny")
@Route(value = "nauczycieloceny", layout = MainLayout.class)
public class TeacherGradesView extends VerticalLayout {

    private final Teacher teacher;
    private Subject chosenSubject;
    private Class chosenClass;

    @Autowired
    private final TeacherService teacherService;
    @Autowired
    private final StudentService studentService;

    private final Grid <Student> klasaGrid = new Grid<>();
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


        /*
        grid - czlonkowie klasy i ich oceny
         */

        Grid<sampleData> gridData = new Grid<>();
        sampleData data = new sampleData("Aleksanda", "Tlalka", 5,"02-04-2021", "sprawdzian", "lorem ipsum");

        gridData.getHeaderRows().clear();
        HeaderRow headerRow = gridData.appendHeaderRow();

        Editor<sampleData> editor = gridData.getEditor();

        Grid.Column<sampleData> nameColumn =  gridData.addColumn(sampleData::getName);  //TODO dodac filtr
        Grid.Column<sampleData> surnameColumn = gridData.addColumn(sampleData::getSurname); //TODO dodac filtr
        Grid.Column<sampleData> gradeColumn = gridData.addColumn(sampleData::getGradeValue).setHeader("Ocena");
        Grid.Column<sampleData> dateColumn = gridData.addColumn(sampleData::getDate).setHeader("Data");
        Grid.Column<sampleData> typeColumn = gridData.addColumn(sampleData::getType).setHeader("Typ");
        Grid.Column<sampleData>  commentColumn = gridData.addColumn(sampleData::getComment).setHeader("Komentarz");
        Grid.Column<sampleData> editColumn = gridData.addComponentColumn(gridDataItem -> {
            Button editButton = new Button ("Modyfikuj");
            //editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            editButton.addClickListener(e-> {
                if(editor.isOpen())
                    editor.cancel();
                gridData.getEditor().editItem(gridDataItem);
            });
            return editButton;
        }).setWidth("150px").setFlexGrow(0);

        Grid.Column<sampleData> deleteColumn = gridData.addComponentColumn(gridDataItem->{
            Button deleteButton = new Button ("Usuń");
            deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            deleteButton.addClickListener(e->{

                //TODO Usuwanie
                //TODO popup z potwierdzeniem dokonania akcji

            });
            return deleteButton;
        }).setWidth("150px").setFlexGrow(0);

        List<sampleData> dataList = new ArrayList<>();
        dataList.add(data);

        ListDataProvider<sampleData> dataProvider = new ListDataProvider<>(dataList);
        gridData.setDataProvider(dataProvider);

        StudentFilter studentFilter = new StudentFilter(dataProvider);

        headerRow.getCell(nameColumn).setComponent(createFilterHeader("Name",studentFilter::setName));
        headerRow.getCell(surnameColumn).setComponent(createFilterHeader("Surname",studentFilter::setSurname));


        /* --------------------------- EDYTOWANIE
        Binder<Person> binder = new Binder<>(Person.class);
        editor.setBinder(binder);
        editor.setBuffered(true);

        TextField firstNameField = new TextField();
        firstNameField.setWidthFull();
        binder.forField(firstNameField)
                .asRequired("First name must not be empty")
                 .withStatusLabel(firstNameValidationMessage)
                  .bind(Person::getFirstName, Person::setFirstName);
         firstNameColumn.setEditorComponent(firstNameField);

        TextField lastNameField = new TextField();
        lastNameField.setWidthFull();
        binder.forField(lastNameField).asRequired("Last name must not be empty")
            .withStatusLabel(lastNameValidationMessage)
            .bind(Person::getLastName, Person::setLastName);
        lastNameColumn.setEditorComponent(lastNameField);

        EmailField emailField = new EmailField();
        emailField.setWidthFull();
        binder.forField(emailField).asRequired("Email must not be empty")
             .withValidator(new EmailValidator(
                "Please enter a valid email address"))
               .withStatusLabel(emailValidationMessage)
             .bind(Person::getEmail, Person::setEmail);
         emailColumn.setEditorComponent(emailField);

        Button saveButton = new Button("Save", e -> editor.save());
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
            e -> editor.cancel());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON,
            ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveButton,
         cancelButton);
        actions.setPadding(false);
        editColumn.setEditorComponent(actions);

      ________________________________________________________________--
         */


        gridData.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        comboBoxesLayout.add(subjectBox,classBox);
        boxButtonLayout.add(comboBoxesLayout,subjectButton);
        add(boxButtonLayout,addButton,gridData);
    }
    private void classFunction(com.example.ElectronicGrade.model.entity.Class Class){

        chosenClass = Class;
        addButton.setEnabled(true);

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
class sampleData{

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    String name;
    String surname;

    int gradeValue;
    String date;
    String comment;
    String type;

    sampleData(String name, String surname,int gradeValue, String date, String type, String comment){
        this. name = name;
        this.surname= surname;
        this.gradeValue = gradeValue;
        this.date = date;
        this.comment = comment;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public String getSurname(){
         return surname;
    }
    public int getGradeValue() {
        return gradeValue;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getType(){
        return type;
    }
}

class StudentFilter{

    private final ListDataProvider<sampleData> dataProvider;

    private String fullName;
    private String surname;

    public StudentFilter(ListDataProvider<sampleData> dataProvider) {
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


    public boolean test(sampleData data) {
        boolean matchesFullName = matches(data.getName(), fullName);
        boolean matchesEmail = matches(data.getSurname(), surname);

        return matchesFullName && matchesEmail;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty() || value
                .toLowerCase().contains(searchTerm.toLowerCase());
    }
}
