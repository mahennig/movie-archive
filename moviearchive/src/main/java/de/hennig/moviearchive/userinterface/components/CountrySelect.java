package de.hennig.moviearchive.userinterface.components;

import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.TwinColSelect;
import de.hennig.moviearchive.domain.core.CountryData;
import de.hennig.moviearchive.domain.core.GenreData;

import java.util.HashSet;
import java.util.Set;

public class CountrySelect extends TwinColSelect<String> {

    public CountrySelect(String caption) {
        this.setCaption(caption);
        this.setHeight("60%");
        this.setItemCaptionGenerator((ItemCaptionGenerator<String>) item -> item);
        initCountryBox();
    }

    private void initCountryBox() {
        this.setItems(loadCountries());
    }

    private Set<String> loadCountries() {
        Set set = new HashSet();
        for (CountryData data : CountryData.values()) {
            set.add(data.getName());
        }
        return set;
    }
}
