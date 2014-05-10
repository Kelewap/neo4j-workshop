package edu.neo4j.workshop.socialnetwork.dao;

import edu.neo4j.workshop.socialnetwork.factories.LanguageFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class LanguageDAO extends AbstractIndexingService {

    @Autowired
    public LanguageDAO(GraphDatabaseService graphDatabaseService, LanguageFactory personFactory) {
        super(graphDatabaseService, personFactory);
    }

}
