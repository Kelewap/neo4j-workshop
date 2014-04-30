package edu.neo4j.workshop.socialnetwork.uploading;

import org.neo4j.graphdb.Node;

/**
 * @author partyks
 */
public class KnowingRelationshipDescription {
    private final Node person1;
    private final Node person2;

    public KnowingRelationshipDescription(Node person1, Node person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    public Node getPerson1() {
        return person1;
    }

    public Node getPerson2() {
        return person2;
    }
}
