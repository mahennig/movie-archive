package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Director;
import de.hennig.moviearchive.userinterface.components.CountryComboBox;
import de.hennig.moviearchive.userinterface.components.GenreSelect;
import de.hennig.moviearchive.util.CollectionUtil;


public class AddMovieLayout extends VerticalLayout {

    private Label layoutName;

    private TextField titleField;
    ComboBox<Director> directorBox;
    private HorizontalLayout titleDirectorContainer;

    private TextArea descriptionArea;
    private GenreSelect genreSelector;

    ComboBox countryComboBox;
    ComboBox<Integer> yearComboBox;
    private HorizontalLayout yearCountryContainer;


    private Button saveButton;
    private Button cancelButton;
    private HorizontalLayout buttonContainer;


    public AddMovieLayout() {
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        layoutName = new Label("Neuen Film hinzufügen");

        titleDirectorContainer = new HorizontalLayout();
        titleField = new TextField("Titel");
        titleField.setIcon(VaadinIcons.MOVIE);
        directorBox = new ComboBox<>("Regisseur");
        directorBox.setIcon(VaadinIcons.USER);

        descriptionArea = new TextArea("Handlung");
        descriptionArea.setSizeFull();
        genreSelector = new GenreSelect("Genre");

        yearCountryContainer = new HorizontalLayout();
        countryComboBox = new CountryComboBox("Land");
        yearComboBox = new ComboBox<>("Erscheinungsjahr");
        initYearComboBox();
        descriptionArea = new TextArea("Handlung");

        saveButton = new Button("Speichern", VaadinIcons.SAFE);
        cancelButton = new Button("Abbrechen", VaadinIcons.CLOSE);
        buttonContainer = new HorizontalLayout();
    }

    private void buildLayout() {
        this.addComponent(layoutName);

        titleDirectorContainer.addComponent(titleField);
        titleDirectorContainer.addComponent(directorBox);
        this.addComponent(titleDirectorContainer);

        this.addComponent(descriptionArea);
        this.addComponent(genreSelector);

        yearCountryContainer.addComponent(countryComboBox);
        yearCountryContainer.addComponent(yearComboBox);
        this.addComponent(yearCountryContainer);

        buttonContainer.addComponent(saveButton);
        buttonContainer.addComponent(cancelButton);
        this.addComponent(buttonContainer);
        this.setResponsive(true);
    }

    private void initYearComboBox() {
        yearComboBox.setItems(CollectionUtil.getYearCollection());
    }

}
