package de.hennig.moviearchive.userinterface.views;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.core.FilterAttributes;
import de.hennig.moviearchive.services.MovieCrudLogic;
import de.hennig.moviearchive.services.PersonService;
import de.hennig.moviearchive.services.dataprovider.MovieDataProvider;
import de.hennig.moviearchive.userinterface.components.MovieForm;
import de.hennig.moviearchive.userinterface.components.MovieGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;

@SpringView(name = MovieView.VIEW_NAME)
public class MovieView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "GRID";

    private MovieGrid grid;

    @Autowired
    private MovieForm.MovieFormFactory formFactory;

    MovieForm form;

    Panel filterBar;

    @Autowired
    private MovieDataProvider dataProvider;

    @Autowired
    private PersonService personService;

    @Autowired
    private MovieCrudLogic.MovieCrudLogicFactory logicFactory;

    private ConfigurableFilterDataProvider<Movie, Void, FilterAttributes> filterDataProvider;
    private TextField searchFilter;
    private ComboBox<String> startingLetterFilter;

    private final HorizontalLayout gridContainer = new HorizontalLayout();

    Label header;
    Button newMovie;

    FilterAttributes filter = new FilterAttributes();

    private MovieCrudLogic viewLogic;

    private HorizontalLayout createTopBar() {
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setStyleName("background-color:coral");

        newMovie = new Button("Neuen Film hinzufügen");
        header = new Label("Filmdatenbank");
        header.addStyleName("h2");
        searchFilter = new TextField();
        searchFilter.setPlaceholder("Suche ...");
        searchFilter.addValueChangeListener(event -> filterDataProvider.setFilter(filter.setSearchText(event.getValue())));

        startingLetterFilter = new ComboBox<>();
        startingLetterFilter.setItems("#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        startingLetterFilter.addValueChangeListener(valueChangeEvent ->

                filterDataProvider.setFilter(filter.setCapital(valueChangeEvent.getValue())));

        topLayout.addComponentsAndExpand(header);
        topLayout.addComponent(startingLetterFilter);
        topLayout.addComponent(searchFilter);
        topLayout.addComponent(newMovie);
        newMovie.addClickListener(e -> viewLogic.newMovie());
        topLayout.setComponentAlignment(header, Alignment.TOP_LEFT);
        topLayout.setComponentAlignment(newMovie, Alignment.TOP_RIGHT);
        topLayout.setMargin(new MarginInfo(false, true, false, true));
        return topLayout;
    }

    private Panel createFilterBar() {
        Panel panel = new Panel();
        panel.setCaption("Film Details");
        form = formFactory.createForm(viewLogic);
        updateDirectorBox();
        updateActorsBox();
        panel.setContent(form);
        return panel;
    }

    public void updateActorContainer(Movie movie){
        form.setActors(movie.getCast());
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        viewLogic.enter(event.getParameters());
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

    public void updateDirectorBox() {
        form.setDirectors(personService.getAllPerson());
    }

    public void updateActorsBox() {
        form.setActors(personService.getAllPerson());
    }

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
        barAndGridLayout.addComponentsAndExpand(grid);
        barAndGridLayout.setMargin(new MarginInfo(false, false, true, false));
        barAndGridLayout.setSpacing(false);

        addComponentsAndExpand(barAndGridLayout);

        filterBar = createFilterBar();
        addComponent(filterBar);
        setComponentAlignment(filterBar, Alignment.BOTTOM_CENTER);

        viewLogic.init();
    }

    private void loadApplicationContext() {
        this.logicFactory = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(MovieCrudLogic.MovieCrudLogicFactory.class);
        this.dataProvider = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(MovieDataProvider.class);
        this.formFactory = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(MovieForm.MovieFormFactory.class);
        this.personService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
                .getBean(PersonService.class);
    }

    public void hideForm() {
        form.setVisible(false);
    }

    public void showForm() {
        form.setVisible(true);
    }

}
