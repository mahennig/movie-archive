package de.hennig.moviearchive.userinterface.components;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;
import de.hennig.moviearchive.domain.core.CountryData;
import de.hennig.moviearchive.domain.core.GenreData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountryComboBox extends ComboBox<String> {

    public CountryComboBox(String caption) {
        this.setCaption(caption);
        initCountryComboBox();
    }

    private void initCountryComboBox() {
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
