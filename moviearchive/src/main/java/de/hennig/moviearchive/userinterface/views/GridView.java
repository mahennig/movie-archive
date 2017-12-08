package de.hennig.moviearchive.userinterface.views;

import com.beust.jcommander.internal.Sets;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.services.GenreService;
import de.hennig.moviearchive.userinterface.components.HorizontalLine;
import de.hennig.moviearchive.userinterface.components.MovieGrid;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SpringView(name = GridView.VIEW_NAME)
public class GridView extends VerticalLayout implements View {

    @Autowired
    GenreService genreService;

    public static final String VIEW_NAME = "GRID";

    // Filter elements
    private final TextField titleFilter = new TextField();
    private final TextField directorFilter = new TextField();
    private final ListSelect<Genre> genreFilter = new ListSelect();

    private final VerticalLayout filterBar = new VerticalLayout();
    private final MovieGrid movieGrid = new MovieGrid();
    private final HorizontalLayout gridContainer = new HorizontalLayout();

    public GridView() {
        loadApplicationContext();
        buildLayout();
        addComponent(gridContainer);
        setComponentAlignment(gridContainer, Alignment.TOP_CENTER);
        setSizeFull();
        setMargin(new MarginInfo(false, false, false, false));
    }

    private void buildLayout() {
        gridContainer.addComponent(filterBar);
        initFilterBar();
        gridContainer.addComponentsAndExpand(movieGrid);
    }

    private void initFilterBar() {
        filterBar.setWidth(250, UNITS_PIXELS);
        filterBar.addComponent(titleFilter);
        filterBar.addComponent(directorFilter);
        filterBar.addComponent(genreFilter);
        Set<Genre> genres = new HashSet<>();
        genres = genreService.findAll();
        genreFilter.setValue(genres);
        titleFilter.setPlaceholder("Titel ...");
        directorFilter.setPlaceholder("Regisseur ...");
    }

    private void loadApplicationContext() {
        this.genreService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(GenreService.class);
    }
}
