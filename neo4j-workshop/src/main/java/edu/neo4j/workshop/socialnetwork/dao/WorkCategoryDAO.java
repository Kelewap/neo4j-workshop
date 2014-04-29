package edu.neo4j.workshop.socialnetwork.dao;

import edu.neo4j.workshop.socialnetwork.factories.WorkCategoryFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class WorkCategoryDAO extends AbstractIndexingService {

    @Autowired
    public WorkCategoryDAO(GraphDatabaseService graphDatabaseService, WorkCategoryFactory abstractNodeFactory) {
        super(graphDatabaseService, abstractNodeFactory);
    }
}
