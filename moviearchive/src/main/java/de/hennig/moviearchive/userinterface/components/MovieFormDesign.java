package de.hennig.moviearchive.userinterface.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
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

    protected TextField titleField = new TextField("Titel");
    protected ComboBox<Person> directorBox = new ComboBox<>("Regisseur");

    protected TextArea descriptionArea = new TextArea("Handlung");

    protected CountryComboBox countryBox = new CountryComboBox("Land");

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

        VerticalLayout vertContainer1 = new VerticalLayout();
        vertContainer1.setMargin(new MarginInfo(false, false, false, false));
        vertContainer1.addComponent(titleField);
        vertContainer1.addComponent(countryBox);
        inputBar.addComponent(vertContainer1);
        inputBar.setComponentAlignment(vertContainer1, Alignment.TOP_LEFT);


        VerticalLayout vertContainer2 = new VerticalLayout();
        vertContainer2.setMargin(new MarginInfo(false, false, false, false));
        vertContainer2.addComponent(directorBox);
        inputBar.addComponent(vertContainer2);
        vertContainer2.setSizeFull();
        inputBar.setComponentAlignment(vertContainer2, Alignment.TOP_CENTER);

        inputBar.addComponentsAndExpand(descriptionArea);
        inputBar.setComponentAlignment(descriptionArea, Alignment.TOP_RIGHT);

        VerticalLayout vertContainer3 = new VerticalLayout();
        vertContainer3.addComponent(runningTime);
        inputBar.addComponent(vertContainer3);

        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }
}
