package de.hennig.moviearchive.userinterface.components;

import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.*;
import de.hennig.moviearchive.domain.Person;

import static javafx.geometry.Orientation.HORIZONTAL;

public class MovieFormDesign extends VerticalLayout {

    HorizontalLayout inputBar = new HorizontalLayout();

    protected TextField title = new TextField("Titel");
    protected ComboBox<Person> director = new ComboBox<Person>("Regisseur");
    protected TextArea description = new TextArea("Handlung");
    protected TextField runtime = new TextField("Laufzeit in Minuten");
    protected CountryComboBox country = new CountryComboBox("Land");
    protected GenreSelect genre = new GenreSelect("Genre");
    protected YearComboBox year = new YearComboBox("Erscheinungsjahr");
    protected TextField folder = new TextField("Ordner");
    protected TextField page = new TextField("Seite");

    protected ComboBox<Person> actors = new ComboBox<>("Schauspieler");
    protected Button addActorButton = new Button(VaadinIcons.PLUS);
    protected Button removeActorButton = new Button(VaadinIcons.MINUS);

    protected Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
    protected Button discardButton = new Button("Verwerfen", VaadinIcons.CROSS_CUTLERY);
    protected Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
    protected Button cancelButton = new Button("Abbrechen", VaadinIcons.FILE_REMOVE);

    public MovieFormDesign() {

        super.setSizeFull();

        inputBar.setResponsive(true);
        inputBar.setSizeFull();

        // Title, Year, Director
        VerticalLayout vertContainer1 = new VerticalLayout();
        vertContainer1.setMargin(new MarginInfo(false, false, false, false));
        vertContainer1.setSizeFull();
        vertContainer1.addComponentsAndExpand(title, country, director);
        title.setSizeFull();
        country.setSizeFull();
        director.setSizeFull();
        inputBar.addComponent(vertContainer1);
        inputBar.setComponentAlignment(vertContainer1, Alignment.TOP_LEFT);

        // Country, Folder, Page
        VerticalLayout vertContainer2 = new VerticalLayout();
        vertContainer2.setSizeFull();
        vertContainer2.setMargin(new MarginInfo(false, false, false, false));
        vertContainer2.addComponents(year, folder, page);
        year.setSizeFull();
        folder.setSizeFull();
        page.setSizeFull();
        inputBar.addComponent(vertContainer2);
        inputBar.setComponentAlignment(vertContainer2, Alignment.TOP_RIGHT);

        // Actors and Runtime
        VerticalLayout vertContainer3 = new VerticalLayout();
        vertContainer3.setMargin(new MarginInfo(false, false, false, false));
        description.setWidth("100%");
        inputBar.addComponent(vertContainer3);
        HorizontalLayout buttonActorContainer = new HorizontalLayout();
        buttonActorContainer.setMargin(new MarginInfo(false, false, false, false));
        buttonActorContainer.addComponents(addActorButton, removeActorButton);
        buttonActorContainer.setComponentAlignment(addActorButton, Alignment.MIDDLE_CENTER);
        buttonActorContainer.setComponentAlignment(removeActorButton, Alignment.MIDDLE_CENTER);
        buttonActorContainer.setSizeFull();
        vertContainer3.addComponents(actors, buttonActorContainer, runtime);
        vertContainer3.setComponentAlignment(runtime, Alignment.BOTTOM_CENTER);
        actors.setSizeFull();
        addActorButton.setSizeFull();
        removeActorButton.setSizeFull();
        runtime.setSizeFull();
        inputBar.addComponent(vertContainer3);
        inputBar.setComponentAlignment(vertContainer3, Alignment.TOP_RIGHT);

        // Description
        VerticalLayout vertContainer4 = new VerticalLayout();
        vertContainer4.setMargin(new MarginInfo(false, false, false, false));
        vertContainer4.addComponents(description);
        description.setWidth("100%");
        description.setHeight(190, Unit.PIXELS); //222px

        inputBar.addComponents(vertContainer4);
        inputBar.setComponentAlignment(vertContainer4, Alignment.TOP_LEFT);


        // Genre
        VerticalLayout vertContainer5 = new VerticalLayout();
        vertContainer5.setMargin(new MarginInfo(false, false, false, false));
        vertContainer5.addComponents(genre);
        vertContainer5.setComponentAlignment(genre, Alignment.MIDDLE_LEFT);
        genre.setHeight(190, Unit.PIXELS);
        genre.setWidth("100%");
        inputBar.addComponent(vertContainer5);
        inputBar.setComponentAlignment(vertContainer5, Alignment.TOP_LEFT);

        // Buttons
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }
}
