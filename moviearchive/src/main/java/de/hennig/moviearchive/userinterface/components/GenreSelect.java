package de.hennig.moviearchive.userinterface.components;

import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.Label;
import com.vaadin.ui.TwinColSelect;
import de.hennig.moviearchive.domain.core.GenreData;

import java.util.HashSet;
import java.util.Set;

public class GenreSelect extends TwinColSelect<String> {

    public GenreSelect(String caption) {
        this.setCaption(caption);
        this.setHeight(50, Unit.PIXELS);
        this.setItemCaptionGenerator((ItemCaptionGenerator<String>) item -> item);
        initGenreBox();
    }

    private void initGenreBox() {
        this.setItems(loadGenres());
    }

    private Set<String> loadGenres() {
        Set set = new HashSet();
        for (GenreData data : GenreData.values()) {
            set.add(data.getName());
        }
        return set;
    }
}
