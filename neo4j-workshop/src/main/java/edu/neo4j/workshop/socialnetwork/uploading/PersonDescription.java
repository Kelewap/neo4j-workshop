package edu.neo4j.workshop.socialnetwork.uploading;

/**
 * @author partyks
 */
public class PersonDescription {
    private final String name;
    private final String username;

    public PersonDescription(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
