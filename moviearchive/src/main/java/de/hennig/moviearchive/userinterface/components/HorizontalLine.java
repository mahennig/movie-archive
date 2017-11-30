package de.hennig.moviearchive.userinterface.components;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class HorizontalLine extends Label {
    public HorizontalLine(int width) {
        String html = "<hr noshade style=\"background-color: black; height: 10px; width: " + width + "px;\">";
        Label label = new Label(html, ContentMode.HTML);
    }
}
