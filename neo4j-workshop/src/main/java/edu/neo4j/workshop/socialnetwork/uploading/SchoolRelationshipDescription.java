package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.relations.StudentSchoolRelationship;
import org.neo4j.graphdb.Node;

/**
 * @author partyks
 */
public class SchoolRelationshipDescription {
    private final Node person;
    private final Node school;
    private final StudentSchoolRelationship relationship;

    public SchoolRelationshipDescription(Node person, Node school, StudentSchoolRelationship relationship) {
        this.person = person;
        this.school = school;
        this.relationship = relationship;
    }

    public Node getPerson() {
        return person;
    }

    public Node getSchool() {
        return school;
    }

    public StudentSchoolRelationship getRelationship() {
        return relationship;
    }
}
