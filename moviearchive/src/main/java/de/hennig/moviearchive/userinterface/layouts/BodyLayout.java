package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Director;
import de.hennig.moviearchive.services.CountryService;
import de.hennig.moviearchive.userinterface.components.CountryComboBox;
import de.hennig.moviearchive.userinterface.components.GenreSelect;
import de.hennig.moviearchive.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;


public class BodyLayout extends VerticalLayout {

    @Autowired
    CountryService countryService;

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


    public BodyLayout() {
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        titleDirectorContainer = new HorizontalLayout();
        titleField = new TextField("Titel");
        directorBox = new ComboBox<>("Regisseur");

        descriptionArea = new TextArea("Handlung");
        descriptionArea.setSizeFull();
        genreSelector = new GenreSelect("Genre");

        yearCountryContainer = new HorizontalLayout();
        countryComboBox = new CountryComboBox("Land");
        yearComboBox = new ComboBox<>("Erscheinungsjahr");
        initYearComboBox();
        descriptionArea = new TextArea("Handlung");
        saveButton = new Button("Speichern");
        cancelButton = new Button("Abbrechen");
        buttonContainer = new HorizontalLayout();
    }

    private void buildLayout() {
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
