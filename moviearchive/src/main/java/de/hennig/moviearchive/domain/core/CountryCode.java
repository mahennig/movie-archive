package de.hennig.moviearchive.domain.core;

public enum CountryCode {
    de("Deutschland"),
    fr("Frankreich"),
    gb("Vereinigtes Königreich (GB)"),
    usa("USA"),
    ru("Russland"),
    in("Indien"),
    chn("China"),
    jp("Japan"),
    esp("Spanien"),
    it("Italien"),
    aus("Österreich"),
    ch("Schweiz");

    private final String name;

    CountryCode(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
