package de.hennig.moviearchive.userinterface.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import de.hennig.moviearchive.domain.Person;

public class MovieFormDesign extends VerticalLayout {

    HorizontalLayout inputBar = new HorizontalLayout();

    protected TextField title = new TextField("Titel");
    protected ComboBox<Person> director = new ComboBox<Person>("Regisseur");
    protected TextArea description = new TextArea("Handlung");

    protected CountrySelect country = new CountrySelect("Land");
    protected GenreSelect genre = new GenreSelect("Genre");

    protected ActorSelect actorSelect = new ActorSelect();

    protected YearTextField year = new YearTextField();
    protected TextField folder = new TextField("Ordner");
    protected TextField page = new TextField("Seite");

    protected Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
    protected Button discardButton = new Button("Verwerfen", VaadinIcons.CROSS_CUTLERY);
    protected Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
    protected Button cancelButton = new Button("Abbrechen", VaadinIcons.FILE_REMOVE);


    public MovieFormDesign() {

        super.setSizeFull();

        inputBar.setResponsive(true);
        inputBar.setSizeFull();

        // Title, Year, Director
        VerticalLayout basicInputContainer = new VerticalLayout();
        basicInputContainer.setMargin(new MarginInfo(false, false, false, false));


        HorizontalLayout folderContainer = new HorizontalLayout();
        folderContainer.addComponentsAndExpand(folder, page);
        //title.setSizeFull();
        //director.setSizeFull();

        basicInputContainer.setSizeFull();
        basicInputContainer.addComponentsAndExpand(title, director, year, folderContainer);

        inputBar.addComponent(basicInputContainer);
        inputBar.setComponentAlignment(basicInputContainer, Alignment.TOP_LEFT);


        VerticalLayout vert2 = new VerticalLayout();
        vert2.setMargin(new MarginInfo(false, false, false, false));
        //country.setWidth(300, Unit.PIXELS);
        //country.setHeight(190, Unit.PIXELS);
        country.setSizeFull();
        vert2.addComponents(country);
        inputBar.addComponent(vert2);
        inputBar.setComponentAlignment(vert2, Alignment.TOP_LEFT);

        // Actors and Runtime

        inputBar.addComponent(actorSelect);
        inputBar.setComponentAlignment(actorSelect, Alignment.TOP_LEFT);

        // Genre
        VerticalLayout vertContainer5 = new VerticalLayout();
        vertContainer5.setMargin(new MarginInfo(false, false, false, false));
        vertContainer5.addComponents(genre);
        vertContainer5.setComponentAlignment(genre, Alignment.MIDDLE_CENTER);
        genre.setHeight(190, Unit.PIXELS);
        genre.setWidth(300, Unit.PIXELS);
        inputBar.addComponent(vertContainer5);
        inputBar.setComponentAlignment(vertContainer5, Alignment.TOP_LEFT);

        // DESC
        VerticalLayout vertContainer4 = new VerticalLayout();
        vertContainer4.setMargin(new MarginInfo(false, false, false, false));
        vertContainer4.addComponentsAndExpand(description);
        description.setWidth("100%");
        description.setHeight(190, Unit.PIXELS); //222px

        inputBar.addComponents(vertContainer4);
        inputBar.setComponentAlignment(vertContainer4, Alignment.TOP_LEFT);

        // Buttons
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }
}
