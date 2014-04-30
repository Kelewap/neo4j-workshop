package edu.neo4j.workshop.socialnetwork.uploading;

/**
 * @author partyks
 */
public class SchoolDescription {
    private final String name;
    private final String abbreviation;

    public SchoolDescription(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
