package de.hennig.moviearchive.userinterface.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

public class MovieFormDesign extends VerticalLayout {

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

    protected Image image = new Image("Bild");
    protected PopupView popup;


    protected Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
    protected Button discardButton = new Button("Verwerfen", VaadinIcons.CROSS_CUTLERY);
    protected Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
    protected Button cancelButton = new Button("Abbrechen", VaadinIcons.FILE_REMOVE);


    public MovieFormDesign() {

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

        // Image
        //initImage();

        // Buttons
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(saveButton, discardButton, deleteButton, cancelButton);
        addComponents(inputBar, buttonBar);
    }

    private void initImage() {
        image.setSource(new ExternalResource("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWiZVjBlrvZ9h01TB6m-nrcbjOnMcsoqqT0qWxFcbWjThUsGFr"));
        inputBar.addComponent(image);
        inputBar.setComponentAlignment(image, Alignment.TOP_RIGHT);
        VerticalLayout popupContent = new VerticalLayout();
        popupContent.addComponent(new TextField("Textfield"));
        popupContent.addComponent(new Button("Button"));

// The component itself
        popup = new PopupView(null, new TextField());
    }
}
