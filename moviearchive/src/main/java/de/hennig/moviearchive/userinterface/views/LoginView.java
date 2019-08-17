package de.hennig.moviearchive.userinterface.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@SpringView(name = LoginView.ROUTE)
@Slf4j
public class LoginView extends VerticalLayout implements View {

    public static final String ROUTE = "login";

    TextField username = new TextField("Nutzername");
    TextField password = new TextField("Passwort");
    Button loginButton = new Button("Press me!");

    @PostConstruct
    private void init() {
        this.addComponents(username, password, loginButton);
        loginButton.addClickListener(e -> process());
    }

    private void process() {
        this.getUI().getNavigator().navigateTo(MovieView.ROUTE);
    }

    private boolean authenticate(String user, String pw) {
        if (user != null && pw != null) return true;
        return false;
    }

}