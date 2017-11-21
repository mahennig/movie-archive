package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import de.hennig.moviearchive.userinterface.forms.MovieForm;


@Theme("valo")
@Title("Film Datenbank")
public class HomeUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        MovieForm movieForm = new MovieForm();
        movieForm.setVisible(true);
        setContent(movieForm);
    }



}
