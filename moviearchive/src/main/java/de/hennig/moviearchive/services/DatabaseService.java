package de.hennig.moviearchive.services;

import org.h2.jdbcx.JdbcConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseService {

    private static DatabaseService databaseService;
    private JdbcConnectionPool connPool;
    private DatabaseService() throws IOException {
        // initialize a connection pool for H2
        connPool = JdbcConnectionPool.create("jdbc:h2:tcp://localhost/C:/DEV/Repo/movie/h2/app_db", "sa",
                "pass");
        System.out.println("DatabaseService instantiated...");
    }
    public static DatabaseService getInstance() throws IOException {
        if (databaseService == null) {
            databaseService = new DatabaseService();
        }
        return databaseService;
    }
    public Connection getConnection() throws SQLException {
        return connPool.getConnection();
    }
}
