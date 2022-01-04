package com.example.ElectronicGrade.views.login;

import com.example.ElectronicGrade.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Log In")
@Route(value = "logIn", layout = MainLayout.class)
public class LoginForm extends Composite<LoginOverlay> {

    public LoginForm(){

        LoginOverlay loginOverlay = getContent();
        loginOverlay.setTitle("Dziennik Elektroniczny");
        loginOverlay.setDescription("Akademckie Liceum Ogólnokształcące\n" +
                "Politechniki Wrocławskiej");
        loginOverlay.setOpened(true);

        loginOverlay.addLoginListener(loginEvent -> {
            //LOGIN ACTION - VERIFICATION ETC

            //SAMPLE ONE:
            if("admin".equals(loginEvent.getUsername())){
                if("admin".equals(loginEvent.getPassword()))
                    Notification.show("Wkrótce zostaniesz zalogowany");
                else
                    Notification.show("Podane hasło jest niepoprawne");
            }
            else
                Notification.show("Podany login jest niepoprawny");
        });

        loginOverlay.setForgotPasswordButtonVisible(false);

    }
}

/*public class LoginView extends Composite<VerticalLayout> {
    public LoginView(){
        VerticalLayout layout = getContent();
        LoginForm loginForm = new LoginForm();
        layout.add(loginForm);
        layout.setSizeFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }
}*/