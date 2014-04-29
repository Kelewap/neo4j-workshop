package edu.neo4j.workshop.socialnetwork.dao;

import edu.neo4j.workshop.socialnetwork.factories.PersonFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class PersonDAO extends AbstractIndexingService {

    @Autowired
    public PersonDAO(GraphDatabaseService graphDatabaseService, PersonFactory personFactory) {
        super(graphDatabaseService, personFactory);
    }

}
