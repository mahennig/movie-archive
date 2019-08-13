package de.hennig.moviearchive.userinterface.components;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.server.Page;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.services.MovieCrudLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.Optional;
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

    private MovieForm() {
        this.setVisible(false);
    }

    public void setActors(Set<Person> persons) {
        ComboBox<Person> actorBox = actorSelect.getActorBox();
        actorBox.setItems(persons);
    }

    public void setDirectors(Set<Person> persons) {
        director.setItems(persons);
    }

    private void onSave() {
        if (binder.writeBeanIfValid(currentMovie)) {
            viewLogic.saveMovie(currentMovie);
            viewLogic.hideSelectView();
        }
    }

    private void onAddActor() {
        if (currentMovie == null) {
            sendNoMovieSelectedNotication();
        }
        ComboBox<Person> actorBox = actorSelect.getActorBox();
        viewLogic.addActor(currentMovie, actorBox.getValue());
    }

    private void onRemoveActor() {
        if (currentMovie == null) {
            sendNoMovieSelectedNotication();
        }
        viewLogic.removeActor(currentMovie, actorSelect.getValue());
    }

    private void sendNoMovieSelectedNotication() {
        new Notification("Warnung", "Es wurde kein Film ausgewählt!", Notification.Type.WARNING_MESSAGE, true)
                .show(Page.getCurrent());
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
        boolean movieNotNull = currentMovie != null;
        saveButton.setEnabled(!validationErrors && changes && movieNotNull);
        discardButton.setEnabled(changes);
    }


    /*
        Binding all fields with current Movie Bean
     */
    @PostConstruct
    private void init() {
        initDirectorBox();
        initActorBox();
        binder.forField(title).bind(Movie::getTitle, Movie::setTitle);
        binder.forField(description).bind(Movie::getDescription, Movie::setDescription);
        binder.forField(year)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getYear, Movie::setYear);
        binder.forField(genre).bind(Movie::getGenres, Movie::setGenres);
        binder.forField(director).bind(Movie::getDirector, Movie::setDirector);
        binder.forField(country).bind(Movie::getCountry, Movie::setCountry);
        binder.forField(folder)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getFolder, Movie::setFolder);
        binder.forField(page)
                .withConverter(new StringToIntegerConverter("Bitte eine Zahl eingeben."))
                .withNullRepresentation(0)
                .bind(Movie::getPage, Movie::setPage);
        binder.forField(actorSelect.getActorContainer()).bind(Movie::getCast, Movie::setCast);

        saveButton.addClickListener(event -> onSave());
        cancelButton.addClickListener(event -> viewLogic.cancelMovie());
        deleteButton.addClickListener(event -> onDelete());
        discardButton.addClickListener(event -> setUpData());
        actorSelect.getActorAddBtn().addClickListener(event -> onAddActor());
        binder.addStatusChangeListener(this::updateButtons);
    }

    private void initDirectorBox() {
        director.setItemCaptionGenerator(Person::getName);
        director.setNewItemProvider(inputString -> createNewPerson(inputString));
    }

    private void initActorBox() {
        ComboBox<Person> actorBox = actorSelect.getActorBox();
        actorBox.setNewItemProvider(inputString -> createNewPerson(inputString));
    }

    public Optional<Person> createNewPerson(String name) {
        Person newPerson = new Person();
        newPerson.setName(name);
        viewLogic.newPerson(newPerson);
        viewLogic.updatePeople();
        return Optional.of(newPerson);
    }


}
