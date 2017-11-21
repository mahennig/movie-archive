package de.hennig.moviearchive.userinterface.forms;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;


public class MovieForm extends VerticalLayout {

    private Button save;
    private Button cancel;

    private HorizontalLayout buttonContainer;

    private TextField title;

    public MovieForm() {
        initComponents();
    }

    private void initComponents() {
        title = new TextField();

        buttonContainer = new HorizontalLayout();

        save = new Button("Speichern");
        cancel = new Button("Abbrechen");

        buttonContainer.addComponent(save);
        buttonContainer.addComponent(cancel);

    }


}
