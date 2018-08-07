package de.hennig.moviearchive.domain.core;

public enum CountryData {

    usa("USA"),
    china("China"),
    uk("England"),
    india("Indien"),
    egypt("Ägypten"),
    iran("Iran"),
    japan("Japan"),
    france("Frankreich"),
    spain("Spanien"),
    germany("Deutschland"),
    turkey("Türkei"),
    italy("Italien"),
    argentina("Argentinien"),
    other("Sonstiges");


    private final String name;

    CountryData(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
