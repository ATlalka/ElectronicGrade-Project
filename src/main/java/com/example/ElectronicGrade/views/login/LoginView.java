package com.example.ElectronicGrade.views.login;

import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "Log In", layout = MainLayout.class)
public class LoginView extends Composite<VerticalLayout> {

    private TextField name;
    private Button sayHello;
    private LoginForm loginForm;

    public LoginView(){
       // getContent().add(new TextField("Username"), new PasswordField("Password"));
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        getContent().add(name, sayHello);
        loginForm = new LoginForm();
        getContent().add(loginForm);
    }
}
