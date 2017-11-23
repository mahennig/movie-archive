package de.hennig.moviearchive.userinterface.components;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;
import de.hennig.moviearchive.domain.core.CountryData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CountryComboBox extends ComboBox<CountryData> {

    public CountryComboBox(String caption) {
        this.setCaption(caption);

        List<CountryData> countriesData = new ArrayList<CountryData>();
        countriesData.add(new CountryData("USA"));
        countriesData.add(new CountryData("Deutschland"));

        this.setItems(countriesData);
        this.setItemIconGenerator(country -> {
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            String flagName = null;
            switch (country.getName()) {
                case "USA":
                    flagName = "flag-usa";
                    break;
                case "Deutschland":
                    flagName = "flag-germany";
                    break;
            }

            Resource resource = new FileResource(new File(
                    (basepath + "/WEB-INF/classes/flags/" + flagName + ".png")));
            return resource;
        });
        this.setItemCaptionGenerator(new ItemCaptionGenerator<CountryData>() {
            @Override
            public String apply(CountryData item) {
                return item.getName();
            }
        });

    }
}
