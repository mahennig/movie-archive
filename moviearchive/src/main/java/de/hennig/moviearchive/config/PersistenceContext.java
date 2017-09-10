
package de.hennig.moviearchive.config;

import org.apache.log4j.Logger;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableNeo4jRepositories(basePackages = "de.hennig.moviearchive.repositories")
@EnableTransactionManagement
@org.springframework.context.annotation.Configuration
public class PersistenceContext {

	Logger logger = Logger.getLogger(getClass());

	@Bean
	public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
		final Neo4jTransactionManager transactionManager = new Neo4jTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	public SessionFactory sessionFactory(Configuration configuration) {
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = new SessionFactory(configuration, "de.hennig.moviearchive.repositories");
			
		} catch (Exception e) {
			logger.error(e);
		}
		return sessionFactory;
	}
}
