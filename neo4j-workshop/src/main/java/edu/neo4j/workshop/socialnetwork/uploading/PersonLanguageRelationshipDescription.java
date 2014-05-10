package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.relations.LaungageRelationship;
import org.neo4j.graphdb.Node;

/**
 * @author partyks
 */
public class PersonLanguageRelationshipDescription {
    private final Node username;
    private final Node language;
    private final LaungageRelationship level;

    public PersonLanguageRelationshipDescription(Node username, Node language, LaungageRelationship level) {
        this.username = username;
        this.language = language;
        this.level = level;
    }

    public Node getUsername() {
        return username;
    }

    public Node getLanguage() {
        return language;
    }

    public LaungageRelationship getLevel() {
        return level;
    }
}
