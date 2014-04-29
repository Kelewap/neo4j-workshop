package edu.neo4j.workshop.socialnetwork.services;

import edu.neo4j.workshop.socialnetwork.relations.KnowingRelationship;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class KnowRelationshipService {

    private final GraphDatabaseService graphDatabaseService;

    @Autowired
    public KnowRelationshipService(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
    }

    public void associate(Node first, Node second){
        first.createRelationshipTo(second, KnowingRelationship.KNOWS);
    }

}
