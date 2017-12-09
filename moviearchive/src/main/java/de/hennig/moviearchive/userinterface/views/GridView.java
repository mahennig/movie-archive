package de.hennig.moviearchive.userinterface.views;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
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
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.GenreService;
import de.hennig.moviearchive.services.dataprovider.MovieDataProvider;
import de.hennig.moviearchive.userinterface.components.HorizontalLine;
import de.hennig.moviearchive.userinterface.components.MovieGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SpringView(name = GridView.VIEW_NAME)
public class GridView extends VerticalLayout implements View {

	@Autowired
	MovieDataProvider dataProvider;

	@Autowired
	GenreService genreService;

	private ConfigurableFilterDataProvider<Movie, Void, String> filterDataProvider;

	public static final String VIEW_NAME = "GRID";

	// Filter elements
	private final TextField titleFilter = new TextField();
	private final TextField directorFilter = new TextField();
	private final ListSelect<Genre> genreFilter = new ListSelect();

	private final VerticalLayout filterBar = new VerticalLayout();
	private MovieGrid movieGrid;
	private final HorizontalLayout gridContainer = new HorizontalLayout();

	public GridView() {
		loadApplicationContext();
		initMovieGrid();
		buildLayout();
		addComponentsAndExpand(gridContainer);
		setComponentAlignment(gridContainer, Alignment.MIDDLE_CENTER);
		setMargin(new MarginInfo(true, false, true, false));
		setSizeFull();
	}

	private void buildLayout() {
		gridContainer.addComponent(filterBar);
		initFilterBar();
		gridContainer.addComponentsAndExpand(movieGrid);
		movieGrid.setSizeFull();
	}

	private void initMovieGrid() {
		movieGrid = new MovieGrid();
		// movieGrid.asSingleSelect().addValueChangeListener(
		// event -> viewLogic.rowSelected(grid.getSelectedRow()));

		filterDataProvider = dataProvider.withConfigurableFilter();
		movieGrid.setDataProvider(filterDataProvider);
	}

	private void initFilterBar() {
		filterBar.setWidth(250, Unit.PIXELS);
		filterBar.addComponent(titleFilter);
		filterBar.addComponent(directorFilter);
		filterBar.addComponent(genreFilter);
		genreFilter.setItems(genreService.findAll());
		titleFilter.setPlaceholder("Titel");
		directorFilter.setPlaceholder("Regisseur");
	}

	private void loadApplicationContext() {
		this.genreService = WebApplicationContextUtils
				.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
				.getBean(GenreService.class);
		this.dataProvider = WebApplicationContextUtils
				.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
				.getBean(MovieDataProvider.class);
	}
}
