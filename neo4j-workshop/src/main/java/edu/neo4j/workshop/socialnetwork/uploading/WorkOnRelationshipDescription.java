package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.relations.PersonWorkRelationship;
import edu.neo4j.workshop.socialnetwork.relations.properties.PersonWorkExperience;
import org.neo4j.graphdb.Node;

/**
 * @author partyks
 */
public class WorkOnRelationshipDescription {
    private final Node person;
    private final Node workCategory;
    private final PersonWorkRelationship relationship;
    private final PersonWorkExperience workExperience;

    public WorkOnRelationshipDescription(Node person, Node workCategory, PersonWorkRelationship relationship, PersonWorkExperience workExperience) {
        this.person = person;
        this.workCategory = workCategory;
        this.relationship = relationship;
        this.workExperience = workExperience;
    }

    public Node getPerson() {
        return person;
    }

    public Node getWorkCategory() {
        return workCategory;
    }

    public PersonWorkRelationship getRelationship() {
        return relationship;
    }

    public PersonWorkExperience getWorkExperience() {
        return workExperience;
    }
}
