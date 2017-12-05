package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HeaderLayout extends HorizontalLayout {

    Label title = new Label("Film Datenbank");
    Button createMovie = new Button("Neuen Film hinzufügen");


    public HeaderLayout() {
        prepareLayout();
    }

    private void prepareLayout() {
        this.setMargin(new MarginInfo(false, true, false, true));

        this.addComponent(title);
        this.setComponentAlignment(title, Alignment.TOP_LEFT);

        this.addComponent(createMovie);
        this.setComponentAlignment(createMovie, Alignment.TOP_RIGHT);


    }


}
