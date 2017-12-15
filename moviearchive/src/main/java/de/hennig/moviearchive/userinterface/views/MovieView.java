package de.hennig.moviearchive.userinterface.views;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Movie;
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

    @Autowired
    private MovieDataProvider dataProvider;

    @Autowired
    private PersonService personService;

    @Autowired
    private MovieCrudLogic.MovieCrudLogicFactory logicFactory;

    private ConfigurableFilterDataProvider<Movie, Void, String> filterDataProvider;
    private TextField filter;

    private final HorizontalLayout gridContainer = new HorizontalLayout();

    Label header;
    Button newMovie;

    private MovieCrudLogic viewLogic;

    private HorizontalLayout createTopBar() {
        newMovie = new Button("Neuen Film hinzufügen");
        header = new Label("Filmdatenbank");
        filter = new TextField();
        filter.setPlaceholder("Suche ...");
        filter.addValueChangeListener(event -> filterDataProvider.setFilter(event.getValue()));
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.addComponentsAndExpand(header);
        topLayout.addComponent(filter);
        topLayout.addComponent(newMovie);
        newMovie.addClickListener(e -> viewLogic.newMovie());
        topLayout.setComponentAlignment(header, Alignment.TOP_LEFT);
        topLayout.setComponentAlignment(newMovie, Alignment.TOP_RIGHT);
        topLayout.setMargin(new MarginInfo(false, false, true, false));
        return topLayout;
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
        barAndGridLayout.setMargin(false);
        barAndGridLayout.setSpacing(false);

        addComponent(barAndGridLayout);

        form = formFactory.createForm(viewLogic);
        form.setPerson(personService.getAllPerson());
        //form.setVisible(false);
        addComponent(form);

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

}
