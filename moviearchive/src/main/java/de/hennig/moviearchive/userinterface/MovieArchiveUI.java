package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.userinterface.layouts.BodyLayout;
import de.hennig.moviearchive.userinterface.layouts.HeaderLayout;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Title("Film Datenbank")
@SpringUI
public class MovieArchiveUI extends UI {

    HeaderLayout header;
    BodyLayout body;

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout main = new VerticalLayout();

        header = new HeaderLayout();
        body = new BodyLayout();

        main.addComponent(header);
        main.addComponent(body);

        setContent(main);
    }

    public static MovieArchiveUI get() {
        return (MovieArchiveUI) UI.getCurrent();
    }

    @WebServlet(value = "/*", asyncSupported = true, initParams = {
            @WebInitParam(name = "heartbeatInterval", value = "30")})
    @VaadinServletConfiguration(ui = MovieArchiveUI.class, productionMode = false)
    public static class Servlet extends SpringVaadinServlet {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }
}
