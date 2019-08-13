package de.hennig.moviearchive.domain.core;

import lombok.Data;


public class FilterAttributes {

    String searchText;

    String capital;

    public boolean isSearchTextEmpty(){
        return searchText == null || searchText.isEmpty();
    }

    public boolean isCapitalEmpty(){
        return capital == null || capital.isEmpty();
    }

    public boolean hasFilters() {
        return !isSearchTextEmpty() || !isCapitalEmpty();
    }

    public String getSearchText() {
        return searchText;
    }

    public FilterAttributes setSearchText(String searchText) {
        this.searchText = searchText;
        return this;
    }

    public String getCapital() {
        return capital;
    }

    public FilterAttributes setCapital(String capital) {
        this.capital = capital;
        return this;
    }
}
