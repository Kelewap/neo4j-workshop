package edu.neo4j.workshop.socialnetwork.services;

import edu.neo4j.workshop.socialnetwork.relations.PersonWorkRelationship;
import edu.neo4j.workshop.socialnetwork.relations.properties.PersonWorkExperience;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class PersonWorkRelationshipService {

    private final GraphDatabaseService graphDatabaseService;

    @Autowired
    public PersonWorkRelationshipService(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
    }

    public void associate(Node first, Node second, PersonWorkRelationship relationship, PersonWorkExperience experience){
        final Relationship relationshipTo = first.createRelationshipTo(second, relationship);
        relationshipTo.setProperty("yearsOfExperience", experience.getYearsOfExperience());
    }

}
