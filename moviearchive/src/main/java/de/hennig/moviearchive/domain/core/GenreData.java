package de.hennig.moviearchive.domain.core;

public enum GenreData {
    criminal("Kriminalfilm"),
    comedy("Filmkomödie"),
    musical("Musical"),
    war("Kriegsfilm"),
    erotic("Erotikfilm"),
    fantasy("Fantasyfilm"),
    horror("Horrorfilm"),
    action("Actionfilm"),
    thriller("Thriller"),
    drama("Drama"),
    scifi("Science-Fiction-Film");

    private final String name;

    GenreData(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
