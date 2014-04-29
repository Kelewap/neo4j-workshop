package edu.neo4j.workshop.socialnetwork;

/**
 * @author partyks
 */
public class NotUniqueException extends RuntimeException {
    public NotUniqueException(String message) {
        super(message);
    }
}
