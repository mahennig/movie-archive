package de.hennig.moviearchive;

import java.util.LinkedList;
import java.util.List;

public final class DataProviderBuilder {

    private DataProviderBuilder() {
    }

    public static DataProviderBuilder init() {
        return new DataProviderBuilder();
    }

    private List<Object[]> params = new LinkedList<>();

    public DataProviderBuilder add(Object... obj) {
        params.add(obj);
        return this;
    }

    public Object[][] build() {
        return params.toArray(new Object[params.size()][]);
    }
}
