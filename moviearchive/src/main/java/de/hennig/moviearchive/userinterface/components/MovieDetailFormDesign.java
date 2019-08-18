package de.hennig.moviearchive.userinterface.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import javax.swing.*;

public class MovieDetailFormDesign extends VerticalLayout {

    HorizontalLayout inputBar = new HorizontalLayout();

    protected TextField title = new TextField("Titel");
    protected TextField directors = new TextField("Regisseur");
    protected TextField actors = new TextField("Schauspieler");
    protected TextField year = new TextField("Jahr");
    protected TextField runtime = new TextField("Laufzeit");

    protected TextField country = new TextField("Land");
    protected TextField folder = new TextField("Ordner");
    protected TextField page = new TextField("Seite");
    protected TextField genre = new TextField("Genre");

    protected TextArea description = new TextArea("Handlung");
    protected TextField tags = new TextField("Schlagwörter");

    protected Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
    protected Button discardButton = new Button("Verwerfen", VaadinIcons.CROSS_CUTLERY);
    protected Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
    protected Button cancelButton = new Button("Abbrechen", VaadinIcons.FILE_REMOVE);


    public MovieDetailFormDesign() {

        super.setSizeFull();

        inputBar.setResponsive(true);
        inputBar.setSizeFull();

        // Title, Year, Runtime, Director, Actors
        VerticalLayout leftContainer = new VerticalLayout();
        leftContainer.setMargin(new MarginInfo(false, false, false, false));
        leftContainer.setSizeFull();

        HorizontalLayout numberBox = new HorizontalLayout();
        year.setSizeFull();
        runtime.setSizeFull();
        numberBox.addComponentsAndExpand(year, runtime);

        title.setSizeFull();
        numberBox.setSizeFull();
        directors.setSizeFull();
        actors.setSizeFull();
        leftContainer.addComponentsAndExpand(title, numberBox, directors, actors);
        inputBar.addComponent(leftContainer);
        inputBar.setComponentAlignment(leftContainer, Alignment.TOP_LEFT);

        // Country, Genre, Folder, Page
        VerticalLayout middleContainer = new VerticalLayout();
        middleContainer.setMargin(new MarginInfo(false, false, false, false));
        middleContainer.setSizeFull();
        country.setSizeFull();
        genre.setSizeFull();
        folder.setSizeFull();
        page.setSizeFull();
        middleContainer.addComponents(country, genre, folder, page);
        inputBar.addComponent(middleContainer);
        inputBar.setComponentAlignment(middleContainer, Alignment.TOP_CENTER);

        // Description and Tags
        VerticalLayout rightContainer = new VerticalLayout();
        rightContainer.setMargin(new MarginInfo(false, false, false, false));
        rightContainer.setSizeFull();
        description.setSizeFull();
        tags.setSizeFull();
        rightContainer.addComponents(description, tags);
        inputBar.addComponents(rightContainer);
        inputBar.setComponentAlignment(rightContainer, Alignment.TOP_RIGHT);

        // Buttons
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }


}
