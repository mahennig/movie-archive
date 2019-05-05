package de.hennig.moviearchive.services.backup;

import de.hennig.moviearchive.domain.Movie;
import de.hennig.moviearchive.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;


@Component
public class MovieBackupService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MovieService service;

    ResultSetMapper<Movie> resultSetMapper = new ResultSetMapper<Movie>();

    //@Scheduled(fixedRate = 10000)
    void export() {
        logger.info("Creating Database Backup ...");
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:h2:file:~/movie", "sa", "");
            Statement st = conn.createStatement();
            final ResultSet resultSet = st.executeQuery("select * from MOVIE");
            List<Movie> movieList = resultSetMapper.mapRersultSetToObject(resultSet, Movie.class);
            if (movieList != null) {
                for (Movie movie : movieList) {
                    System.out.println(movie);
                }
            } else {
                System.out.println("ResultSet is empty. Please check if database table is empty");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
