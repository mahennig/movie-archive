package de.hennig.moviearchive.userinterface.components;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.Page;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.MovieCrudLogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class MovieDetailForm extends MovieDetailFormDesign {

    private MovieCrudLogic viewLogic;
    private Binder<Movie> binder = new Binder<>(Movie.class);
    private Movie currentMovie;

    @SpringComponent
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static class MovieFormFactory {

        @Autowired
        private ApplicationContext context;

        public MovieDetailForm createForm(MovieCrudLogic logic) {
            MovieDetailForm form = context.getBean(MovieDetailForm.class);
            form.init(logic);
            return form;
        }
    }

    private void init(MovieCrudLogic logic) {
        viewLogic = logic;
    }

    private MovieDetailForm() {
        this.setVisible(false);
    }

    @PostConstruct
    private void init() {
        binder.forField(title).bind(Movie::getTitle, Movie::setTitle);
        binder.forField(description).bind(Movie::getDescription, Movie::setDescription);
        binder.forField(year)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getYear, Movie::setYear);
        binder.forField(runtime)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getRunningTime, Movie::setRunningTime);
        binder.forField(genre).bind(Movie::getGenres, Movie::setGenres);
        binder.forField(directors).bind(Movie::getDirectors, Movie::setDirectors);
        binder.forField(country).bind(Movie::getCountry, Movie::setCountry);
        binder.forField(folder)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getFolder, Movie::setFolder);
        binder.forField(page)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getPage, Movie::setPage);
        binder.forField(actors).bind(Movie::getCast, Movie::setCast);
        binder.forField(tags).bind(Movie::getTags, Movie::setTags);

        saveButton.addClickListener(event -> onSave());
        cancelButton.addClickListener(event -> viewLogic.cancelMovie());
        deleteButton.addClickListener(event -> onDelete());
        discardButton.addClickListener(event -> setUpData());

        binder.addStatusChangeListener(this::updateButtons);
    }

    private void onSave() {
        log.info("About to persist the movie: {}", currentMovie);
        if (binder.writeBeanIfValid(currentMovie)) {
            viewLogic.saveMovie(currentMovie);
            viewLogic.hideSelectView();
        } else {
            Notification.show("Speichern Fehlgeschlagen. Bitte überprüfen Sie die Eingaben.");
        }
    }

    public void editMovie(Movie movie) {
        currentMovie = movie;
        setUpData();
        deleteButton.setEnabled(movie != null && movie.getId() != -1);
        String scrollScript = "window.document.getElementById('" + getId()
                + "').scrollTop = 0;";
        Page.getCurrent().getJavaScript().execute(scrollScript);
    }

    private void onDelete() {
        if (currentMovie != null) {
            viewLogic.deleteMovie(currentMovie);
            viewLogic.hideSelectView();
        } else {
            if (currentMovie == null) {
                sendNoMovieSelectedNotication();
            }
        }
    }

    private void sendNoMovieSelectedNotication() {
        new Notification("Warnung", "Es wurde kein Film ausgewählt!", Notification.Type.WARNING_MESSAGE, true)
                .show(Page.getCurrent());
    }

    private void setUpData() {
        if (currentMovie != null) {
            binder.readBean(currentMovie);
        } else {
            binder.removeBean();
        }
    }

    private void updateButtons(StatusChangeEvent event) {
        boolean changes = event.getBinder().hasChanges();
        boolean validationErrors = event.hasValidationErrors();
        boolean movieNotNull = currentMovie != null;
        saveButton.setEnabled(!validationErrors && changes && movieNotNull);
        discardButton.setEnabled(changes);
    }


}
