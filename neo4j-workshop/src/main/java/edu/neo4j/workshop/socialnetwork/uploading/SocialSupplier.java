package edu.neo4j.workshop.socialnetwork.uploading;

import java.util.List;

/**
 * @author partyks
 */
public interface SocialSupplier<T> {
    T get(List<String> split);
}
