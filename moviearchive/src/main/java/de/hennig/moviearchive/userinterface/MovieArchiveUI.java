package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.userinterface.views.GridView;
import de.hennig.moviearchive.userinterface.views.HeaderView;
import de.hennig.moviearchive.userinterface.views.AboutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Title("Film Datenbank")
@SpringUI
public class MovieArchiveUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider;

    VerticalLayout layout = new VerticalLayout();
    HeaderView headerView = new HeaderView();
    GridView gridView = new GridView();
    AboutView aboutView = new AboutView();

    @Override
    public void init(VaadinRequest request) {
        setContent(layout);
        Responsive.makeResponsive(this);
        layout.addComponent(headerView);
        layout.addComponent(gridView);
        layout.addComponent(aboutView);
        layout.setSizeFull();
        layout.setSpacing(false);
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

    private class ViewChangeHandler implements ViewChangeListener {

        @Override
        public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
        }

    }

    ;


}
