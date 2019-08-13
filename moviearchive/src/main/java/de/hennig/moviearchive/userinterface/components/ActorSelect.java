package de.hennig.moviearchive.userinterface.components;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;

import com.vaadin.ui.*;
import de.hennig.moviearchive.domain.Person;

import java.util.Set;

public class ActorSelect extends VerticalLayout {

    protected ComboBox<Person> actors = new ComboBox<>("Wähle Schauspieler");
    protected Button addActorButton = new Button(VaadinIcons.PLUS);

    protected ListSelect<Person> actorContainer = new ListSelect();

    public ActorSelect() {

        actors.setItemCaptionGenerator(Person::getName);

        this.setMargin(new MarginInfo(false, false, false, false));

        HorizontalLayout componentsContainer = new HorizontalLayout();
        componentsContainer.setMargin(new MarginInfo(false, false, false, false));
        componentsContainer.addComponents(actors, addActorButton);
        componentsContainer.setComponentAlignment(addActorButton, Alignment.BOTTOM_RIGHT);
        addActorButton.setWidth(20, Unit.PIXELS);
        componentsContainer.setComponentAlignment(actors, Alignment.BOTTOM_LEFT);
        componentsContainer.setSizeFull();
        addActorButton.setWidth(50, Unit.PIXELS);
        actors.setSizeFull();

        actorContainer.setSizeFull();
        actorContainer.setItemCaptionGenerator(person -> person.getName());
        actorContainer.setRows(5);

        this.addComponents(componentsContainer, actorContainer);
        this.setComponentAlignment(actorContainer, Alignment.BOTTOM_CENTER);
    }

    public ComboBox getActorBox() {
        return actors;
    }

    public Button getActorAddBtn() {
        return addActorButton;
    }

    public ListSelect<Person> getActorContainer() {
        return actorContainer;
    }

    public void setItems(Set<Person> persons) {

    }

    public Person getValue() {
        return null;
    }


}
