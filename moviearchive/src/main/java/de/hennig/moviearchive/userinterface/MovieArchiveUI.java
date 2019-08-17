package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.ui.themes.BaseTheme;
import de.hennig.moviearchive.userinterface.views.ErrorView;
import de.hennig.moviearchive.userinterface.views.LoginView;
import de.hennig.moviearchive.userinterface.views.MovieView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Title("Film Datenbank")
@Theme("valo")
@SpringUI
@Slf4j
public class MovieArchiveUI extends UI {

    @Autowired
    SpringViewProvider provider;

    VerticalLayout layout = new VerticalLayout();

    VerticalLayout viewContainer = new VerticalLayout();


    @Override
    public void init(VaadinRequest request) {
        buildBasicLayout();
        buildNavigator();
        buildViewContainer();
    }

    private void buildBasicLayout() {
        Responsive.makeResponsive(this);
        Label header = new Label("Filmdatenbank");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.addStyleName("margin-left:auto");
        setContent(layout);
        layout.addComponent(header);
        layout.setComponentAlignment(header, Alignment.TOP_LEFT);
        layout.setSizeFull();
        layout.setSpacing(false);
        layout.setMargin(false);
    }

    private void buildNavigator() {
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(provider);
        navigator.setErrorView(ErrorView.class);
        navigator.addView(MovieView.ROUTE, MovieView.class);
        navigator.addView(LoginView.ROUTE, LoginView.class);

        navigator.navigateTo(LoginView.ROUTE);
    }

    private void buildViewContainer() {
        layout.addComponentsAndExpand(viewContainer);
        layout.setSpacing(false);
        viewContainer.setMargin(false);
        layout.setComponentAlignment(viewContainer, Alignment.TOP_CENTER);
    }

    public static MovieArchiveUI get() {
        return (MovieArchiveUI) UI.getCurrent();
    }

    @WebServlet(value = "/*", name = "MovieArchiveServlet", asyncSupported = true, initParams = {
            @WebInitParam(name = "heartbeatInterval", value = "30")})
    @VaadinServletConfiguration(ui = MovieArchiveUI.class, productionMode = true)
    public static class Servlet extends SpringVaadinServlet {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }

    private class ViewChangeHandler implements ViewChangeListener {

        @Override
        public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
            log.info(event.getParameters());
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            log.info(event.getParameters());
        }

    }

    ;


}
