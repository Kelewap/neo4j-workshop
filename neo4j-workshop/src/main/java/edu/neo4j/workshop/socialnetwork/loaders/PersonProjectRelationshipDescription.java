package edu.neo4j.workshop.socialnetwork.loaders;

import org.neo4j.graphdb.Node;

/**
 * @author partyks
 */
public class PersonProjectRelationshipDescription {
    private final Node person;
    private final Node project;

    public PersonProjectRelationshipDescription(Node person, Node project) {
        this.person = person;
        this.project = project;
    }

    public Node getPerson() {
        return person;
    }

    public Node getProject() {
        return project;
    }
}
