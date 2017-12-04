package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.hennig.moviearchive.domain.Director;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.MovieRepository;
import de.hennig.moviearchive.userinterface.components.CountryComboBox;
import de.hennig.moviearchive.userinterface.components.GenreSelect;
import de.hennig.moviearchive.userinterface.components.YearComboBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SpringComponent
@UIScope
public class MovieEditor extends VerticalLayout {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private TextField titleField = new TextField("Titel");
	ComboBox<Director> directorBox = new ComboBox<>("Regisseur");
	private HorizontalLayout upperItemContainer = new HorizontalLayout();

	private TextArea descriptionArea = new TextArea("Handlung");

	private GenreSelect genreSelector = new GenreSelect("Genre");

	private TextField runningTime = new TextField("Laufzeit");
	private HorizontalLayout middleItemContainer = new HorizontalLayout();

	CountryComboBox countryComboBox = new CountryComboBox("Land");
	YearComboBox yearComboBox = new YearComboBox("Erscheinungsjahr");
	private HorizontalLayout lowerItemContainer = new HorizontalLayout();

	private Button saveButton = new Button("Speichern", VaadinIcons.SAFE);
	private Button deleteButton = new Button("Löschen", VaadinIcons.CLOSE);
	private HorizontalLayout buttonContainer = new HorizontalLayout();

	@Autowired
	MovieRepository movieRepository;

	private Binder<Movie> movieBinder = new Binder<>(Movie.class);
	private Movie selectedMovie = new Movie();

	public MovieEditor() {
		loadApplicationContext();
		configComponents();
		buildLayout();
		initBinder();

		saveButton.addClickListener(e -> saveMovie(selectedMovie));
		deleteButton.addClickListener(e -> movieRepository.delete(selectedMovie));
	}

	private void saveMovie(Movie selectedMovie) {
		if (movieBinder.validate().isOk()) {
			if (selectedMovie != null) {
				movieRepository.save(selectedMovie);
				logger.info("Stored new Movie: " + selectedMovie);
				logger.info(movieRepository.count() + " movies in database.");
				unbindForm();
			} else {
				new Notification("Bitte füllen Sie zunächst die Felder aus.");
				logger.info("Movie is null");
			}
		}
	}
	
	private void unbindForm() {
		movieBinder = new Binder();
		logger.debug("Unbind all forms.");
	}

	private void initBinder() {
		movieBinder.setBean(selectedMovie);
		movieBinder.forField(titleField)
		.asRequired("Movie needs a title.")
		.bind(Movie::getTitle, Movie::setTitle);
	}

	private void configComponents() {
		titleField.setIcon(VaadinIcons.MOVIE);
		directorBox.setIcon(VaadinIcons.USER);

		descriptionArea.setIcon(VaadinIcons.BOOK);
		descriptionArea.setSizeFull();
		descriptionArea.setWidth(this.getWidth() * 3.8f, Unit.PIXELS);
		genreSelector.setHeight(100, Unit.PIXELS);
	}

	private void buildLayout() {
		upperItemContainer.addComponent(titleField);
		upperItemContainer.addComponent(directorBox);
		this.addComponent(upperItemContainer);

		this.addComponent(descriptionArea);
		this.setComponentAlignment(descriptionArea, Alignment.MIDDLE_CENTER);
		this.addComponent(genreSelector);

		middleItemContainer.addComponent(runningTime);
		this.addComponent(middleItemContainer);

		lowerItemContainer.addComponent(countryComboBox);
		lowerItemContainer.addComponent(yearComboBox);
		this.addComponent(lowerItemContainer);

		buttonContainer.addComponent(saveButton);
		buttonContainer.addComponent(deleteButton);
		this.addComponent(buttonContainer);
		this.setMargin(new MarginInfo(false, false, false, true));
	}

	public final void editMovie(Movie movie) {

	}

	private void loadApplicationContext() {
		this.movieRepository = WebApplicationContextUtils
				.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
				.getBean(MovieRepository.class);
	}
}
