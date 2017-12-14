package de.hennig.moviearchive.userinterface.components;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.services.MovieCrudLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MovieForm extends MovieFormDesign {

    MovieCrudLogic viewLogic;

    private Binder<Movie> binder = new Binder<>(Movie.class);

    Movie currentMovie;

    @SpringComponent
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static class MovieFormFactory {

        @Autowired
        private ApplicationContext context;

        public MovieForm createForm(MovieCrudLogic logic) {
            MovieForm form = context.getBean(MovieForm.class);
            form.init(logic);
            return form;
        }
    }

    private void onSave() {
        if (binder.writeBeanIfValid(currentMovie)) {
            viewLogic.saveMovie(currentMovie);
        }
    }

    private void onDelete() {
        if (currentMovie != null) {
            viewLogic.deleteMovie(currentMovie);
        }
    }

    public void setPerson(Set<Person> persons) {
        directorBox.setItems(persons);
    }

    public void editMovie(Movie movie) {
        currentMovie = movie;
        setUpData();

        deleteButton.setEnabled(movie != null && movie.getId() != -1);

        // Scroll to the top
        // As this is not a Panel, using JavaScript
        String scrollScript = "window.document.getElementById('" + getId()
                + "').scrollTop = 0;";
        Page.getCurrent().getJavaScript().execute(scrollScript);
    }

    private void init(MovieCrudLogic logic) {
        viewLogic = logic;
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

        saveButton.setEnabled(!validationErrors && changes);
        discardButton.setEnabled(changes);
    }

    @PostConstruct
    private void init() {
        binder.forField(titleField).bind(Movie::getTitle, Movie::setTitle);
        binder.forField(descriptionArea).bind(Movie::getDescription, Movie::setDescription);

        directorBox.setItemCaptionGenerator(Person::getName);
        binder.forField(directorBox).bind(Movie::getDirector, Movie::setDirector);

        saveButton.addClickListener(event -> onSave());
        cancelButton.addClickListener(event -> viewLogic.cancelMovie());
        deleteButton.addClickListener(event -> onDelete());
        discardButton.addClickListener(event -> setUpData());
        binder.addStatusChangeListener(this::updateButtons);
    }


}
