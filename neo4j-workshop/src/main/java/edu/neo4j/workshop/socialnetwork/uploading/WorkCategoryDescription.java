package edu.neo4j.workshop.socialnetwork.uploading;

/**
 * @author partyks
 */
public class WorkCategoryDescription {
    private final String name;
    private final String abbreviation;

    public WorkCategoryDescription(String name, String abbreviation) {
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
