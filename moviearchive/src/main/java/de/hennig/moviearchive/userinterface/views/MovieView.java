package de.hennig.moviearchive.userinterface.views;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
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

    public static final String ROUTE  = "movie";

    @Autowired
    private MovieDetailForm.MovieFormFactory formFactory;

    @Autowired
    private MovieDataProvider dataProvider;

    @Autowired
    private MovieCrudLogic.MovieCrudLogicFactory logicFactory = WebApplicationContextUtils
            .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
            .getBean(MovieCrudLogic.MovieCrudLogicFactory.class);

    private ConfigurableFilterDataProvider<Movie, Void, FilterAttributes> filterDataProvider;
    private final HorizontalLayout gridContainer = new HorizontalLayout();

    MovieDetailForm form;
    Button newMovie;
    Button randomMovie;

    FilterAttributes filter = new FilterAttributes();
    private TextField searchFilter;
    private ComboBox<String> startingLetterFilter;
    private TextField genreFilter;

    private MovieGrid grid;
    Panel detailPanel;

    private MovieCrudLogic viewLogic;

    @PostConstruct
    private void init() {
        viewLogic = logicFactory.createLogic(this);
        setSizeFull();

        HorizontalLayout topLayout = createTopBar();

        grid = new MovieGrid();
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(grid.getSelectedRow()));
        filterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filterDataProvider);

        VerticalLayout barAndGridLayout = new VerticalLayout();

        barAndGridLayout.addComponent(topLayout);
        barAndGridLayout.setComponentAlignment(topLayout, Alignment.TOP_RIGHT);
        barAndGridLayout.addComponentsAndExpand(grid);
        barAndGridLayout.setMargin(new MarginInfo(false, false, true, false));
        barAndGridLayout.setSpacing(false);

        addComponentsAndExpand(barAndGridLayout);

        detailPanel = createMovieDetailForm();
        addComponent(detailPanel);
        setComponentAlignment(detailPanel, Alignment.BOTTOM_CENTER);

        viewLogic.init();
    }

    private HorizontalLayout createTopBar() {
        HorizontalLayout topLayout = new HorizontalLayout();

        newMovie = new Button("Neuen Film hinzufügen");
        randomMovie = new Button(VaadinIcons.LIGHTBULB);

        searchFilter = new TextField();
        searchFilter.setWidth("250px");
        searchFilter.setPlaceholder("Titel & Personen");
        searchFilter.addValueChangeListener(event -> filterDataProvider.setFilter(filter.setSearchText(event.getValue())));

        startingLetterFilter = new ComboBox<>();
        startingLetterFilter.setPlaceholder("Initiale");
        startingLetterFilter.setItems("#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        startingLetterFilter.addValueChangeListener(valueChangeEvent ->
                filterDataProvider.setFilter(filter.setCapital(valueChangeEvent.getValue())));

        genreFilter = new TextField();
        genreFilter.setPlaceholder("Genre");

        topLayout.addComponents(startingLetterFilter, searchFilter, genreFilter, randomMovie, newMovie);

        newMovie.addClickListener(e -> viewLogic.newMovie());
        randomMovie.addClickListener(e -> viewLogic.randomMovieProposal());
        topLayout.setComponentAlignment(newMovie, Alignment.TOP_RIGHT);
        topLayout.setMargin(new MarginInfo(false, true, false, true));
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
        grid.getSelectionModel().deselectAll();
    }

    public void selectRow(Movie row) {
        grid.getSelectionModel().select(row);
    }

    public Movie getSelectedRow() {
        return grid.getSelectedRow();
    }

    public void refreshGrid(Movie movie) {
        grid.refresh(movie);
    }

    public void editMovie(Movie movie) {
        if (movie != null) {
            form.addStyleName("visible");
            form.setEnabled(true);
        } else {
            form.removeStyleName("visible");
        }
        form.editMovie(movie);
    }

    public void updateMovie(Movie movie) {
        dataProvider.save(movie);
    }

    public void removeMovie(Movie movie) {
        dataProvider.delete(movie);
    }

    private void loadApplicationContext() {
        this.dataProvider = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(MovieDataProvider.class);
        this.formFactory = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(MovieDetailForm.MovieFormFactory.class);

    }

    public void hideForm() {
        form.setVisible(false);
    }

    public void showForm() {
        form.setVisible(true);
    }

}
