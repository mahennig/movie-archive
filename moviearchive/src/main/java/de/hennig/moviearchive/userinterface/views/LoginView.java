package de.hennig.moviearchive.userinterface.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@SpringView(name = LoginView.ROUTE)
@Slf4j
public class LoginView extends VerticalLayout implements View {

    public static final String ROUTE = "login";

    PasswordField password = new PasswordField("Passwort");
    Button loginButton = new Button("Einloggen");

    @PostConstruct
    private void init() {
        this.addComponents(password, loginButton);
        password.setStyleName(ValoTheme.TEXTFIELD_LARGE);
        loginButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
        loginButton.setStyleName(ValoTheme.BUTTON_LARGE);
        loginButton.setIcon(VaadinIcons.KEY);
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addClickListener(e -> process());
    }

    private void process() {
        if (authenticate(password.getValue())) {
            log.info("Authentication successful.");
            this.getUI().getNavigator().navigateTo(MovieView.ROUTE);
        } else {
            log.warn("Authentication failed.");
            Notification.show("Login fehlgeschlagen.", Notification.Type.WARNING_MESSAGE);
        }
    }

    private boolean authenticate(String pw) {
        if ("".equals(pw)) return true;
        return false;
    }

}