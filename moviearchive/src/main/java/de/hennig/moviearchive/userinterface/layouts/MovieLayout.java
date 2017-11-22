package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.ui.VerticalLayout;


public class MovieLayout extends VerticalLayout {

    HeaderLayout header;

    BodyLayout body;

    public MovieLayout() {
        header = new HeaderLayout();
        body = new BodyLayout();
        this.addComponent(header);
        this.addComponent(body);
    }


}
