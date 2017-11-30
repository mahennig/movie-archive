package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import de.hennig.moviearchive.userinterface.components.MovieTable;

@SpringComponent
@UIScope
public class BodyLayout extends HorizontalLayout {

    MovieTable movieGrid = new MovieTable();
    MovieEditor movieEditor = new MovieEditor();

    public BodyLayout() {
        prepareMovieGrid();
        prepareLayout();
    }

    private void prepareMovieGrid() {
        movieGrid.setSizeFull();
    }

    private void prepareLayout() {
        //this.setMargin(new MarginInfo(false, true, false, true));
        this.addComponent(movieGrid);
        this.setComponentAlignment(movieGrid, Alignment.TOP_LEFT);
        this.addComponent(movieEditor);
        this.setComponentAlignment(movieEditor, Alignment.TOP_RIGHT);
    }
}
