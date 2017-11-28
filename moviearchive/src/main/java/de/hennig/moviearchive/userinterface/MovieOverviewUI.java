package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.userinterface.components.MovieGrid;
import de.hennig.moviearchive.userinterface.layouts.BodyLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Title("Film Datenbank")
@SpringUI
public class MovieOverviewUI extends UI {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public void init(VaadinRequest request) {
        MovieGrid grid = new MovieGrid("Meine Filme", movieRepository);
        grid.setSizeFull();

        BodyLayout newMovieLayout = new BodyLayout();

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setMargin(new MarginInfo(true, true, false, true));
        mainLayout.addComponent(grid);
        mainLayout.setComponentAlignment(grid, Alignment.TOP_LEFT);
        mainLayout.addComponent(newMovieLayout);
        mainLayout.setComponentAlignment(newMovieLayout, Alignment.TOP_RIGHT);

        setContent(mainLayout);
    }

    public static MovieOverviewUI get() {
        return (MovieOverviewUI) UI.getCurrent();
    }

    @WebServlet(value = "/*", asyncSupported = true, initParams = {
            @WebInitParam(name = "heartbeatInterval", value = "30")})
    @VaadinServletConfiguration(ui = MovieOverviewUI.class, productionMode = false)
    public static class Servlet extends SpringVaadinServlet {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }
}
