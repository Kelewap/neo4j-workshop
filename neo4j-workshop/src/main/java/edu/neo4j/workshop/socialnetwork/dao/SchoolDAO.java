package edu.neo4j.workshop.socialnetwork.dao;

import edu.neo4j.workshop.socialnetwork.factories.SchoolFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class SchoolDAO extends AbstractIndexingService {

    @Autowired
    public SchoolDAO(GraphDatabaseService graphDatabaseService, SchoolFactory abstractNodeFactory) {
        super(graphDatabaseService, abstractNodeFactory);
    }
}
