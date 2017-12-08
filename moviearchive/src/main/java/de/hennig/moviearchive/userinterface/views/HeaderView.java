package de.hennig.moviearchive.userinterface.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = HeaderView.VIEW_NAME)
public class HeaderView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HEADER";

    Label title = new Label("Film Datenbank");
    Button createMovie = new Button("Neuen Film hinzufügen");
    HorizontalLayout headerContent = new HorizontalLayout();
    HorizontalLayout menuBar = new HorizontalLayout();

    public HeaderView() {
        buildLayout();
        setMargin(new MarginInfo(false, false, false, false));
        addComponent(headerContent);
        setComponentAlignment(headerContent, Alignment.TOP_CENTER);
    }

    private void buildLayout() {
        headerContent.addComponent(title);
        title.addStyleName( "h1" );
        headerContent.addComponentsAndExpand(menuBar);
        menuBar.addComponent(createMovie);
        createMovie.setIcon(VaadinIcons.MOVIE);
        headerContent.setComponentAlignment(title, Alignment.TOP_LEFT);
        menuBar.setComponentAlignment(createMovie, Alignment.TOP_RIGHT);
    }
}
