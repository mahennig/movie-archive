
package de.hennig.moviearchive.config;

import org.apache.log4j.Logger;
import org.neo4j.ogm.authentication.Credentials;
import org.neo4j.ogm.authentication.UsernamePasswordCredentials;
import org.neo4j.ogm.config.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DatabaseConfig {

	private final static Logger logger = Logger.getLogger(DatabaseConfig.class);

	private static final String NEO4J_USER = "neo4j";
	private static final String NEO4J_PASSWORD = "1234";
	private static final String NEO4J_URL = "bolt://127.0.0.1:7687";
	private static final String NEO4J_DRIVER = "org.neo4j.ogm.drivers.bolt.driver.BoltDriver";

	@Bean
	public Configuration configuration() {
		return getDataSourceFromLocalConstants();
	}

	@SuppressWarnings("rawtypes")
	private Configuration getDataSourceFromLocalConstants() {
		logger.info("Attempting to fetch data source from prop file config ...");
		Credentials credentials = new UsernamePasswordCredentials(NEO4J_USER, NEO4J_PASSWORD);
		Configuration configuration = new Configuration();
		configuration.driverConfiguration().setDriverClassName(NEO4J_DRIVER).setURI(NEO4J_URL)
				.setCredentials(credentials).setConnectionPoolSize(150);
		logger.info("Configuration successfully loaded.");
		return configuration;
	}
}
