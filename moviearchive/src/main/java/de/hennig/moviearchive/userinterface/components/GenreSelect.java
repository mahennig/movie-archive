package de.hennig.moviearchive.userinterface.components;

import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.Label;
import com.vaadin.ui.TwinColSelect;
import de.hennig.moviearchive.domain.core.CountryData;
import de.hennig.moviearchive.domain.core.GenreData;

public class GenreSelect extends TwinColSelect<GenreData> {

    public GenreSelect(String caption) {
        this.setCaption(caption);

        this.setHeight("60%");

        this.setItems(new GenreData("Drama"), new GenreData("Komödie"));

        this.addSelectionListener(event ->
                        new Label("Selected: " + event.getNewSelection()));

        this.setItemCaptionGenerator(new ItemCaptionGenerator<GenreData>() {
            @Override
            public String apply(GenreData item) {
                return item.getName();
            }
        });

    }
}
