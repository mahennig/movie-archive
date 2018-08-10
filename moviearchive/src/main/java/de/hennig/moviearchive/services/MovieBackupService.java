package de.hennig.moviearchive.services;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import de.hennig.moviearchive.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MovieBackupService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MovieService service;

    @Scheduled(cron = "0 0 18 * * ?")
    void export() {
        logger.info("Creating Database Backup ...");
        service.findAll();
        ColumnPositionMappingStrategy mapStrategy = new ColumnPositionMappingStrategy();
        mapStrategy.setType(Movie.class);


    }
}
