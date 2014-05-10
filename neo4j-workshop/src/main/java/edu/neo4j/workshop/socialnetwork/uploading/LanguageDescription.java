package edu.neo4j.workshop.socialnetwork.uploading;

/**
 * @author partyks
 */
public class LanguageDescription {
    private final String shortcut;
    private final String description;

    public LanguageDescription(String shortcut, String description) {
        this.shortcut = shortcut;
        this.description = description;
    }

    public String getShortcut() {
        return shortcut;
    }

    public String getDescription() {
        return description;
    }
}
