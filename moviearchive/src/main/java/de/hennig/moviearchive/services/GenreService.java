package de.hennig.moviearchive.services;

import de.hennig.moviearchive.domain.core.CountryCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenreService {

    public List<String> getGenres() {
        return Stream.of(CountryCode.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
