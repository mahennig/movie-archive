package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.userinterface.components.HorizontalLine;
import de.hennig.moviearchive.userinterface.components.MovieGrid;

@SpringComponent
@UIScope
public class BodyLayout extends VerticalLayout {

    Label filterLabel = new Label("Filter nach einem bestimmten Film ... ");
    FilterLayout filterBar = new FilterLayout();
    HorizontalLine seperator = new HorizontalLine(100);
    HorizontalLayout tableContainer = new HorizontalLayout();
    MovieEditor movieEditor = new MovieEditor();

    public BodyLayout() {
        prepareLayout();
    }

    private void prepareLayout() {
        this.setMargin(new MarginInfo(false, true, false, true));
        this.addComponent(filterLabel);
        this.addComponent(filterBar);
        //this.setComponentAlignment(filterBar, Alignment.TOP_LEFT);
        this.addComponent(seperator);
        tableContainer.addComponent(movieEditor);
        tableContainer.setComponentAlignment(movieEditor, Alignment.TOP_RIGHT);
        this.addComponent(tableContainer);
    }
}
