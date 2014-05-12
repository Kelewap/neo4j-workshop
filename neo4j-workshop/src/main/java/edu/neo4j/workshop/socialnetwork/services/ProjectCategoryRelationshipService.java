package edu.neo4j.workshop.socialnetwork.services;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class ProjectCategoryRelationshipService {

    private final GraphDatabaseService graphDatabaseService;

    @Autowired
    public ProjectCategoryRelationshipService(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
    }

    public void associate(Node first, Node second){
        first.createRelationshipTo(second, ProjectRelanthip.RELATED);
    }

}
