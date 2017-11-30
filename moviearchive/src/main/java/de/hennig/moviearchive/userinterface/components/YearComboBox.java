package de.hennig.moviearchive.userinterface.components;

import com.vaadin.ui.ComboBox;
import de.hennig.moviearchive.util.CollectionUtil;

public class YearComboBox extends ComboBox<Integer> {

    public YearComboBox(String caption) {
        this.setCaption(caption);
        this.setItems(CollectionUtil.getYearCollection());
    }
}
