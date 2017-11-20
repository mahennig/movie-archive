package de.hennig.moviearchive;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import de.hennig.moviearchive.userinterface.HomeUI;
import de.hennig.moviearchive.userinterface.LandingPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = HomeUI.class)
public class MovieArchiveServlet extends VaadinServlet {

    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
    }

}
