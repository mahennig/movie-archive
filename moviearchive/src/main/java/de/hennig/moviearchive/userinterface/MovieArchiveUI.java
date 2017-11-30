package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.userinterface.components.HorizontalLine;
import de.hennig.moviearchive.userinterface.layouts.BodyLayout;
import de.hennig.moviearchive.userinterface.layouts.HeaderLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Title("Film Datenbank")
@SpringUI
public class MovieArchiveUI extends UI {

    @Autowired
    MovieRepository movieRepository;

    VerticalLayout main = new VerticalLayout();
    HeaderLayout header = new HeaderLayout();
    BodyLayout body = new BodyLayout();
    HorizontalLine seperator = new HorizontalLine(1000);


    @Override
    public void init(VaadinRequest request) {

        main.addComponent(header);
        main.addComponent(seperator);
        main.setComponentAlignment(seperator, Alignment.MIDDLE_CENTER);
        main.addComponent(body);
        setContent(main);
    }

    public static MovieArchiveUI get() {
        return (MovieArchiveUI) UI.getCurrent();
    }

    @WebServlet(value = "/*", name = "MovieArchiveServlet", asyncSupported = true, initParams = {
            @WebInitParam(name = "heartbeatInterval", value = "30")})
    @VaadinServletConfiguration(ui = MovieArchiveUI.class, productionMode = false)
    public static class Servlet extends SpringVaadinServlet {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }
}
