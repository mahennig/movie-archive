package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.GenreService;
import de.hennig.moviearchive.userinterface.components.MovieGrid;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Set;

public class FilterLayout extends HorizontalLayout {

    GenreService genreService;

    private final TextField titleFilter = new TextField();
    private final TextField directorFilter = new TextField();
    private final ListSelect<Genre> genreFilter = new ListSelect();

    MovieGrid movieGrid = new MovieGrid();

    private final VerticalLayout filterContainer = new VerticalLayout();

    //private final MovieGrid movieGrid = new MovieGrid();

    public FilterLayout() {
        loadApplicationContext();
        prepareFilterFields();
        addComponent(filterContainer);
        //addComponent(movieGrid);

    }

    private void prepareFilterFields() {
        filterContainer.addComponent(titleFilter);
        filterContainer.addComponent(directorFilter);
        filterContainer.addComponent(genreFilter);
        titleFilter.setPlaceholder("Titel ...");
        directorFilter.setPlaceholder("Regisseur ...");
        titleFilter.addValueChangeListener(this::onTitleFilterTextChange);
        genreFilter.setData(genreService.findAll());
    }


    private void onTitleFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Movie> dataProvider = (ListDataProvider<Movie>) movieGrid.getDataProvider();
        dataProvider.addFilter(Movie::getTitle, s -> caseInsensitiveContains(s, event.getValue()));
    }

    private void onGenreFilterTextChange(HasValue.ValueChangeEvent<Set<Genre>> event) {
        ListDataProvider<Movie> dataProvider = (ListDataProvider<Movie>) movieGrid.getDataProvider();
        dataProvider.addFilter(Movie::getGenres, s -> containsGenres(s, event.getValue()));
    }

    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }

    private Boolean containsGenres(Set<Genre> where, Set<Genre> what) {
        return true;
    }

    private void loadApplicationContext() {
        this.genreService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(GenreService.class);
    }
}
