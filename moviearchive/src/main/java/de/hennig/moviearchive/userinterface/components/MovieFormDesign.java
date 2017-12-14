package de.hennig.moviearchive.userinterface.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.hennig.moviearchive.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieFormDesign extends VerticalLayout {

    Window subwindow;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    protected TextField titleField = new TextField("Titel");
    protected ComboBox<Person> directorBox = new ComboBox<>("Regisseur");

    protected TextArea descriptionArea = new TextArea("Handlung");

    protected GenreSelect genreSelector = new GenreSelect("Genre");

    protected TextField runningTime = new TextField("Laufzeit");

    protected CountryComboBox countryComboBox = new CountryComboBox("Land");
    protected YearComboBox yearComboBox = new YearComboBox("Erscheinungsjahr");

    protected Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
    protected Button discardButton = new Button("Verwerfen", VaadinIcons.CROSS_CUTLERY);
    protected Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
    protected Button cancelButton = new Button("Abbrechen", VaadinIcons.FILE_REMOVE);

    public MovieFormDesign() {
        HorizontalLayout inputBar = new HorizontalLayout();
        inputBar.addComponent(titleField);
        inputBar.addComponent(directorBox);
        inputBar.addComponentsAndExpand(descriptionArea);
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }

}
