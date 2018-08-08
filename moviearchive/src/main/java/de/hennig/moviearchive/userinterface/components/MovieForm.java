package de.hennig.moviearchive.userinterface.components;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.converter.StringToIntegerConverter;
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

    private MovieForm() {

    }

    public void setActors(Set<Person> persons) {
        actors.setItems(persons);
    }

    public void setDirectors(Set<Person> persons) {
        director.setItems(persons);
    }

    private void onSave() {
        if (binder.writeBeanIfValid(currentMovie)) {
            viewLogic.saveMovie(currentMovie);
        }
    }

    private void onAddActor() {
        viewLogic.addActor(currentMovie, actors.getValue());
    }

    private void onRemoveActor(){
        viewLogic.removeActor(currentMovie, actors.getValue());
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
        binder.forField(genre).bind(Movie::getGenres, Movie::setGenres);
        binder.forField(director).bind(Movie::getDirector, Movie::setDirector);
        binder.forField(country).bind(Movie::getCountry, Movie::setCountry);
        binder.forField(runtime)
                .withConverter(new StringToIntegerConverter("Value must be a integer"))
                .withNullRepresentation(0)
                .bind(Movie::getRunningTime, Movie::setRunningTime);
        binder.forField(folder)
                .withConverter(new StringToIntegerConverter("Value must be a integer"))
                .withNullRepresentation(0)
                .bind(Movie::getFolder, Movie::setFolder);
        binder.forField(page)
                .withConverter(new StringToIntegerConverter("Value must be a integer"))
                .withNullRepresentation(0)
                .bind(Movie::getPage, Movie::setPage);

        saveButton.addClickListener(event -> onSave());
        cancelButton.addClickListener(event -> viewLogic.cancelMovie());
        deleteButton.addClickListener(event -> onDelete());
        discardButton.addClickListener(event -> setUpData());
        addActorButton.addClickListener(event -> onAddActor());
        removeActorButton.addClickListener(event -> onRemoveActor());
        binder.addStatusChangeListener(this::updateButtons);
    }

    private void initDirectorBox() {
        director.setItemCaptionGenerator(Person::getName);
        director.setNewItemHandler(inputString -> {

            Person newPerson = new Person();
            newPerson.setName(inputString);
            viewLogic.newPerson(newPerson);

            viewLogic.updateDirectors();
            director.setSelectedItem(newPerson);
        });
    }

    private void initActorBox() {
        actors.setItemCaptionGenerator(Person::getName);
        actors.setNewItemHandler(inputString -> {

            Person newPerson = new Person();
            newPerson.setName(inputString);
            viewLogic.newPerson(newPerson);

            viewLogic.updateDirectors();
            actors.setSelectedItem(newPerson);
        });
    }




}
