package edu.neo4j.workshop.socialnetwork.services;

import edu.neo4j.workshop.socialnetwork.relations.StudentSchoolRelationship;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class StudentSchoolRelationshipService {

    private final GraphDatabaseService graphDatabaseService;

    @Autowired
    public StudentSchoolRelationshipService(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
    }

    public void associate(Node first, Node second, StudentSchoolRelationship relationship){
        first.createRelationshipTo(second, relationship);
    }

}
