
package de.hennig.moviearchive.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author mhennig
 * 
 *         Umbrella repository for all complex query related database requests.
 *         Normally returning more than one entity type.
 *
 */
public interface QueryRepository extends GraphRepository {

	@Query("MATCH (n), ()-[r]-() DELETE n,r")
	void purgeDatabase();

	@Query("Match (n) Return count(n)")
	Integer getNodeCount();

	@Query("Match ()-[r]-() Return count(r)")
	Integer getRelationshipCount();

}
