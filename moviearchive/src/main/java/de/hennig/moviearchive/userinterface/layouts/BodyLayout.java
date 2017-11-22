package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Director;
import de.hennig.moviearchive.domain.core.CountryCode;
import de.hennig.moviearchive.util.CollectionUtil;

import java.util.Calendar;
import java.util.Date;

public class BodyLayout extends VerticalLayout {

    private TextField titleField;


    ComboBox<CountryCode> countryComboBox;
    ComboBox<Integer> yearComboBox;
    private HorizontalLayout additionInfoContainer;

    ComboBox<Director> directorBox;

    private Button saveButton;
    private Button cancelButton;
    private HorizontalLayout buttonContainer;

    private TextArea descriptionArea;


    public BodyLayout() {
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        titleField = new TextField("Titel");
        saveButton = new Button("Speichern");
        cancelButton = new Button("Abbrechen");
        buttonContainer = new HorizontalLayout();
        additionInfoContainer = new HorizontalLayout();
        countryComboBox = new ComboBox<>("Land");
        yearComboBox = new ComboBox<>("Erscheinungsjahr");
        initYearComboBox();
        descriptionArea = new TextArea("Handlung");
        directorBox = new ComboBox<>("Regisseur");
    }

    private void buildLayout() {
        this.addComponent(titleField);

        this.addComponent(descriptionArea);

        this.addComponent(directorBox);

        additionInfoContainer.addComponent(countryComboBox);
        additionInfoContainer.addComponent(yearComboBox);
        this.addComponent(additionInfoContainer);

        buttonContainer.addComponent(saveButton);
        buttonContainer.addComponent(cancelButton);
        this.addComponent(buttonContainer);
        this.setResponsive(true);
    }

    private void initYearComboBox() {
        yearComboBox.setItems(CollectionUtil.getYearCollection());
    }

    private void initCountryComboBox() {
        //yearComboBox.setItemCaptionGenerator(CountryCode::getName);
    }
}
