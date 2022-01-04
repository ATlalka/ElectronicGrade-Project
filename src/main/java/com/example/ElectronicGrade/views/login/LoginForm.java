package com.example.ElectronicGrade.views.login;

import com.example.ElectronicGrade.views.StudentMainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.*;


@PageTitle("Log In")
@Route(value = "login", layout = StudentMainLayout.class)
public class LoginForm extends Composite<LoginOverlay> implements BeforeEnterObserver {
    private final LoginOverlay loginOverlay = getContent();
    public LoginForm(){


        loginOverlay.setTitle("Dziennik Elektroniczny");
        loginOverlay.setDescription("Akademckie Liceum Ogólnokształcące\n" +
                "Politechniki Wrocławskiej");
        loginOverlay.setOpened(true);
        loginOverlay.setAction("login");

        /*
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

         */

        loginOverlay.setForgotPasswordButtonVisible(false);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")){
            loginOverlay.setError(true);
        }
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