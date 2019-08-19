package de.hennig.moviearchive.services;


import com.vaadin.spring.annotation.SpringComponent;
import de.hennig.moviearchive.domain.Movie;
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


    private MovieCrudLogic() {
    }

    private void init(MovieView view) {
        this.view = view;
    }

    public void cancelMovie() {
        view.clearSelection();
        view.showMovieDetailForm(null);
        view.hideForm();
    }

    public void saveMovie(Movie movie) {
        view.updateMovie(movie);
        view.clearSelection();
        view.refreshGrid(movie);
    }

    public void deleteMovie(Movie movie) {
        view.clearSelection();
        view.showMovieDetailForm(null);
        view.removeMovie(movie);
    }

    public void editMovie(Movie movie) {
        view.showMovieDetailForm(movie);
    }


    public void newMovie() {
        view.clearSelection();
        view.showMovieDetailForm(new Movie());
    }


    public void randomMovieProposal(Movie movie) {
        view.selectMovie(movie);
    }


    public void rowSelected(Movie movie) {
        editMovie(movie);
    }

    public void hideSelectView() {
        view.hideForm();
    }

}

