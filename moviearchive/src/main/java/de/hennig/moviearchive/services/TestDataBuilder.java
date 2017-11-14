package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.domain.builder.MovieBuilder;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping
public class TestDataBuilder {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/createTestMovie", method = RequestMethod.GET, produces = {"application/json"})
    @Transactional
    public @ResponseBody
    String persistFolder() {
        Movie movie = createMovie();
        movieRepository.save(movie);
        return movie + " created";
    }

    @RequestMapping(value = "/getMovies", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    List<Movie> sizeFolder() {
        return Lists.newArrayList(movieRepository.findAll());
    }

    private Movie createMovie() {
        MovieBuilder builder = new MovieBuilder();
        builder.withTitle("TestMovie");
        return builder.build();
    }


}
