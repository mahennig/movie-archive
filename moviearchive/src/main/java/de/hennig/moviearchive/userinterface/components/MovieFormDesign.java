package de.hennig.moviearchive.userinterface.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Person;

import static javafx.geometry.Orientation.HORIZONTAL;

public class MovieFormDesign extends VerticalLayout {

    protected TextField title = new TextField("Titel");
    protected ComboBox<Person> director = new ComboBox<>("Regisseur");
    protected TextArea description = new TextArea("Handlung");
    protected CountryComboBox country = new CountryComboBox("Land");
    protected GenreSelect genre = new GenreSelect("Genre");
    protected Slider runningTime = new Slider(1, 300);
    protected YearComboBox year = new YearComboBox("Erscheinungsjahr");
    protected TextField folder = new TextField("Ordner");
    protected TextField page = new TextField("Seite");
    protected TextField actor = new TextField("Schauspieler");
    protected Button addActorButton = new Button(VaadinIcons.PLUS);

    protected Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
    protected Button discardButton = new Button("Verwerfen", VaadinIcons.CROSS_CUTLERY);
    protected Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
    protected Button cancelButton = new Button("Abbrechen", VaadinIcons.FILE_REMOVE);

    public MovieFormDesign() {
        HorizontalLayout inputBar = new HorizontalLayout();
        inputBar.setResponsive(true);
        inputBar.setSizeFull();

        // Title, Year, Director
        VerticalLayout vertContainer1 = new VerticalLayout();
        vertContainer1.setMargin(new MarginInfo(false, false, false, false));
        vertContainer1.addComponents(title, year, director);
        inputBar.addComponent(vertContainer1);
        inputBar.setComponentAlignment(vertContainer1, Alignment.TOP_LEFT);

        // Country, Folder and Page
        VerticalLayout vertContainer2 = new VerticalLayout();
        vertContainer2.setMargin(new MarginInfo(false, false, false, false));
        vertContainer2.addComponents(country, folder, page);
        inputBar.addComponent(vertContainer2);
        inputBar.setComponentAlignment(vertContainer2, Alignment.TOP_CENTER);

        // Description and Running Time
        VerticalLayout vertContainer3 = new VerticalLayout();
        vertContainer3.setMargin(new MarginInfo(false, false, false, false));
        vertContainer3.addComponents(description, runningTime);
        runningTime.setOrientation(SliderOrientation.HORIZONTAL);
        runningTime.setCaption("Laufzeit in Minuten");
        runningTime.setWidth(250, Unit.PIXELS);
        description.setWidth(250, Unit.PIXELS);
        inputBar.addComponents(vertContainer3);
        inputBar.setComponentAlignment(vertContainer3, Alignment.TOP_CENTER);

        // Genre
        VerticalLayout vertContainer4 = new VerticalLayout();
        vertContainer4.setMargin(new MarginInfo(false, false, false, false));
        vertContainer4.addComponents(genre);
        genre.setHeight(190, Unit.PIXELS);
        inputBar.addComponent(vertContainer4);
        inputBar.setComponentAlignment(vertContainer4, Alignment.TOP_CENTER);

        // Actors
        VerticalLayout vertContainer5 = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        vertContainer5.setMargin(new MarginInfo(false, false, false, false));
        vertContainer5.addComponent(horizontalLayout);
        horizontalLayout.addComponents(actor, addActorButton);
        horizontalLayout.setComponentAlignment(addActorButton, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setComponentAlignment(actor, Alignment.MIDDLE_LEFT);
        inputBar.addComponent(vertContainer5);
        inputBar.setComponentAlignment(vertContainer5, Alignment.TOP_RIGHT);


        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }
}
