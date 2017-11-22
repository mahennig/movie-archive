package de.hennig.moviearchive.userinterface;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import de.hennig.moviearchive.userinterface.layouts.MovieLayout;


@Title("Film Datenbank")
public class HomeUI extends UI {

    private Button save;

    @Override
    protected void init(VaadinRequest request) {
        MovieLayout form = new MovieLayout();
        setContent(form);
    }



}
