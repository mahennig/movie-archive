package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.userinterface.components.MovieGrid;

public class FilterLayout extends HorizontalLayout {

    private final  TextField titleFilter = new TextField();
    private final  TextField directorFilter = new TextField();

    private final MovieGrid movieGrid = new MovieGrid();

    public FilterLayout() {
        this.addComponent(titleFilter);
        this.addComponent(directorFilter);
        addComponentsAndExpand(movieGrid);
    }

    private void prepareFilterFields(){
        titleFilter.setPlaceholder("Titel ...");
        directorFilter.setPlaceholder("Regisseur ...");
        titleFilter.addValueChangeListener(this::onTitleFilterTextChange);
    }

    private void onTitleFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Movie> dataProvider = (ListDataProvider<Movie>) movieGrid.getDataProvider();
        dataProvider.setFilter(Movie::getTitle, s -> caseInsensitiveContains(s, event.getValue()));
    }

    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }
}
