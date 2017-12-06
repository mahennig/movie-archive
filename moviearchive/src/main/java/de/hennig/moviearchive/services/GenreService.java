package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vaadin.data.provider.QuerySortOrder;
import de.hennig.moviearchive.domain.Genre;
import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.repositories.GenreRepository;
import de.hennig.moviearchive.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public ArrayList<Genre> fetchGenres(String filter, int limit, int offset, List<QuerySortOrder> sortOrders) {
        ArrayList<Genre> genres = new ArrayList<>();
        genres = Lists.newArrayList(genreRepository.findAll());
        return genres;
    }

    public int countGenres(String filter) {
        return Lists.newArrayList(genreRepository.findAll()).size();
    }

    public Set<Genre> findAll() {
        return Sets.newHashSet(genreRepository.findAll());
    }
}
