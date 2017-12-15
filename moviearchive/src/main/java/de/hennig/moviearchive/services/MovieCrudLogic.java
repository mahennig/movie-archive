package de.hennig.moviearchive.services;


import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.Person;
import de.hennig.moviearchive.userinterface.MovieArchiveUI;
import de.hennig.moviearchive.userinterface.views.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;

@SpringComponent
public class MovieCrudLogic implements Serializable {

    private MovieView view;

    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @SpringComponent
    public static class MovieCrudLogicFactory {

        @Autowired
        private ApplicationContext context;

        public MovieCrudLogic createLogic(MovieView view) {
            MovieCrudLogic logic = context.getBean(MovieCrudLogic.class);
            logic.init(view);
            return logic;
        }
    }

    @Autowired
    private MovieService movieService;

    @Autowired
    private PersonService personService;

    private MovieCrudLogic() {
    }

    public void init() {
    }

    private void init(MovieView view) {
        this.view = view;
    }

    public void cancelMovie() {
        setFragmentParameter("");
        view.clearSelection();
        view.editMovie(null);
    }

    private void setFragmentParameter(String movieId) {
        String fragmentParameter;
        if (movieId == null || movieId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = movieId;
        }

        Page page = MovieArchiveUI.get().getPage();
        page.setUriFragment(
                "!" + MovieView.VIEW_NAME + "/" + fragmentParameter,
                false);

    }

    public void enter(String movieId) {
        if (movieId != null && !movieId.isEmpty()) {
            if (movieId.equals("new")) {
                newMovie();
            } else {
                try {
                    long mid = Integer.parseInt(movieId);
                    Movie movie = findMovie(mid);
                    view.selectRow(movie);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private Movie findMovie(long movieId) {
        return movieService.getMovie(movieId);
    }

    public void saveMovie(Movie movie) {
        view.clearSelection();
        view.editMovie(null);
        view.updateMovie(movie);
        setFragmentParameter("");
    }

    public void deleteMovie(Movie movie) {
        movieService.deleteMovie(movie);
        view.clearSelection();
        view.editMovie(null);
        view.removeMovie(movie);
        setFragmentParameter("");
    }

    public void editMovie(Movie movie) {
        if (movie == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(movie.getId() + "");
        }
        view.editMovie(movie);
    }

    public void newPerson(Person person) {
        personService.updatePerson(person);
    }

    public void newMovie() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editMovie(new Movie());
    }

    public void updateDirectors() {
        view.updateDirectorBox();
    }

    public void rowSelected(Movie movie) {
        view.editMovie(movie);
    }

}
