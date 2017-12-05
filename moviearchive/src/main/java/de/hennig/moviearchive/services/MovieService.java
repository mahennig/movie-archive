package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hennig.moviearchive.domain.Movie;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    public List<Movie> findAll() {
        return Lists.newArrayList(movieRepo.findAll());
    }

    ArrayList<Movie> fetchEmployees(String filter, int limit, int offset, List<QuerySortOrder> sortOrders) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies = Lists.newArrayList(movieRepo.findAll());
        return movies;
    }

    public int countMovies(String filter) {
        return Lists.newArrayList(movieRepo.findAll()).size();
    }
}
