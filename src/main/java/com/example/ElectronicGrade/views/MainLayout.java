package com.example.ElectronicGrade.views;

import com.example.ElectronicGrade.model.entity.users.Role;
import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.security.SecurityService;
import com.example.ElectronicGrade.views.studentGradeView.StudentGradesView;
import com.example.ElectronicGrade.views.teacherGradeView.TeacherGradesView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "My App", shortName = "My App", enableInstallPrompt = false)
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PageTitle("Main")
@Route("")
public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    public static class MenuItemInfo {

        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    private H1 viewTitle;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        Button logout = new Button ("Log Out", e->securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("m-0", "text-s");

        HorizontalLayout header = new HorizontalLayout(toggle, viewTitle, logout);
        header.expand(viewTitle);
        header.setWidth("100%");
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");
        return header;
    }

    private Component createDrawerContent() {
        com.vaadin.flow.component.html.Section section = null;
        User user = (User) securityService.getAuthenticatedUser();

        if(user!= null){
            H2 appName = new H2("Witaj");
            Label userName = new Label(user.getName());
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            if(user.getRoles().contains(Role.STUDENT))
            {
                Student studentUser = (Student) securityService.getAuthenticatedUser();
                Label className = new Label(studentUser.getStudentClass().toString());
                TextArea classExtension = new TextArea();
                classExtension.setValue(studentUser.getStudentClass().getExtensionsNames());
                classExtension.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
                verticalLayout.add(appName, userName, className, classExtension);
                section = new com.vaadin.flow.component.html.Section(verticalLayout,
                        createNavigation(), createFooter());
                section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
            }
            if(user.getRoles().contains(Role.TEACHER))
            {
                verticalLayout.add(appName, userName);
                section =  new com.vaadin.flow.component.html.Section(verticalLayout,
                        createNavigation(), createFooter());
                section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
            }
        }
        else {
            section = new com.vaadin.flow.component.html.Section(createNavigation(), createFooter());
            section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");

        }
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        VerticalLayout layout = new VerticalLayout();


        User user = (User) securityService.getAuthenticatedUser();
        if(user!= null){
            if(user.getRoles().contains(Role.STUDENT)) {
                layout = createStudentLinks();
            }
            else
            {
                layout = createTeacherLinks();
            }
        }

        nav.add(layout);
        return nav;


    }

    private VerticalLayout createStudentLinks() {

        VerticalLayout links =  new VerticalLayout();

        Button button = new Button("Oceny");
        button.addClickListener( e-> {
            button.getUI().ifPresent(ui -> ui.navigate(StudentGradesView.class));
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setWidthFull();
        links.add(button);

        links.getStyle().set("width", "250px").set("max-width", "100%");
        return links;
    }

    private VerticalLayout createTeacherLinks() {

        VerticalLayout links =  new VerticalLayout();

        Button button = new Button("Oceny");
        button.addClickListener( e-> {
            button.getUI().ifPresent(ui -> ui.navigate(TeacherGradesView.class));
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setWidthFull();
        links.add(button);

        links.getStyle().set("width", "250px").set("max-width", "100%");
        return links;

    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}
