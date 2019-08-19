package de.hennig.moviearchive.userinterface.views;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.core.FilterAttributes;
import de.hennig.moviearchive.services.MovieCrudLogic;
import de.hennig.moviearchive.services.dataprovider.MovieDataProvider;
import de.hennig.moviearchive.userinterface.components.MovieDetailForm;
import de.hennig.moviearchive.userinterface.components.MovieGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;

@SpringView(name = MovieView.ROUTE)
public class MovieView extends VerticalLayout implements View {

    public static final String ROUTE = "movie";

    @Autowired
    private MovieDetailForm.MovieFormFactory formFactory;

    @Autowired
    private MovieDataProvider dataProvider;

    @Autowired
    private MovieCrudLogic.MovieCrudLogicFactory logicFactory = WebApplicationContextUtils
            .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
            .getBean(MovieCrudLogic.MovieCrudLogicFactory.class);

    private ConfigurableFilterDataProvider<Movie, Void, FilterAttributes> filterDataProvider;

    private MovieGrid grid;


    private MovieDetailForm form;

    private MovieCrudLogic viewLogic;

    @PostConstruct
    private void init() {
        viewLogic = logicFactory.createLogic(this);
        setSizeFull();

        HorizontalLayout topLayout = createTopBar();
        createMovieGrid();

        VerticalLayout barAndGridLayout = new VerticalLayout();

        barAndGridLayout.addComponent(topLayout);
        barAndGridLayout.setComponentAlignment(topLayout, Alignment.TOP_RIGHT);
        barAndGridLayout.addComponentsAndExpand(grid);
        barAndGridLayout.setMargin(new MarginInfo(false, false, true, false));
        barAndGridLayout.setSpacing(true);

        addComponentsAndExpand(barAndGridLayout);

        Panel detailPanel = createMovieDetailForm();
        addComponent(detailPanel);
        setComponentAlignment(detailPanel, Alignment.BOTTOM_CENTER);

        viewLogic.init();
    }

    private void createMovieGrid() {
        grid = new MovieGrid();
        grid.setSizeFull();
        grid.addSelectionListener(
                event -> handleSelectionChange());
        filterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filterDataProvider);

    }

    private void handleSelectionChange() {
        Movie selectedMovie = grid.asSingleSelect().getValue();
        if (selectedMovie != null)
            viewLogic.rowSelected(selectedMovie);
    }

    private HorizontalLayout createTopBar() {
        HorizontalLayout topLayout = new HorizontalLayout();
        FilterAttributes filter = new FilterAttributes();

        Button newMovie = new Button("Neuen Film hinzufügen");
        newMovie.setStyleName(ValoTheme.BUTTON_PRIMARY);
        Button randomMovie = new Button(VaadinIcons.LIGHTBULB);

        TextField searchFilter = new TextField();
        searchFilter.setWidth("250px");
        searchFilter.setPlaceholder("Titel & Personen");
        searchFilter.addValueChangeListener(event -> filterDataProvider.setFilter(filter.setSearchText(event.getValue())));

        ComboBox<String> startingLetterFilter = new ComboBox<>();
        startingLetterFilter.setPlaceholder("Initiale");
        startingLetterFilter.setItems("#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        startingLetterFilter.addValueChangeListener(valueChangeEvent ->
                filterDataProvider.setFilter(filter.setCapital(valueChangeEvent.getValue())));

        TextField genreFilter = new TextField();
        genreFilter.setPlaceholder("Genre");

        topLayout.addComponents(startingLetterFilter, searchFilter, genreFilter, randomMovie, newMovie);

        newMovie.addClickListener(e -> viewLogic.newMovie());
        randomMovie.addClickListener(e -> viewLogic.randomMovieProposal(new Movie()));
        topLayout.setComponentAlignment(newMovie, Alignment.TOP_RIGHT);
        topLayout.setMargin(new MarginInfo(false, false, false, true));
        return topLayout;
    }

    private Panel createMovieDetailForm() {
        Panel panel = new Panel();
        panel.setCaption("Film Details");
        form = formFactory.createForm(viewLogic);
        panel.setContent(form);
        return panel;
    }

    public void clearSelection() {
        grid.deselectAll();
    }

    public void selectMovie(Movie movie) {
        grid.select(movie);
    }

    public void refreshGrid(Movie movie) {
        grid.refresh(movie);
    }

    public void showMovieDetailForm(Movie movie) {
        form.editMovie(movie);
        form.setVisible(true);
    }

    public void updateMovie(Movie movie) {
        dataProvider.save(movie);
        refreshGrid(movie);
    }

    public void removeMovie(Movie movie) {
        dataProvider.delete(movie);
    }


    public void hideForm() {
        form.setVisible(false);
    }

}
