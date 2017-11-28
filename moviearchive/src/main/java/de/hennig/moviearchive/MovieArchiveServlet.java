package de.hennig.moviearchive;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import de.hennig.moviearchive.userinterface.MovieOverviewUI;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MovieOverviewUI.class)
@ComponentScan("de.hennig.moviearchive")
public class MovieArchiveServlet extends VaadinServlet {

    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
    }

}
