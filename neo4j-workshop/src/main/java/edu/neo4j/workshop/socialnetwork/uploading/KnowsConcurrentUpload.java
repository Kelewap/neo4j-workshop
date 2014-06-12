package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import org.neo4j.graphdb.GraphDatabaseService;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author partyks
 */
public class KnowsConcurrentUpload extends AbstractConcurrentUploader<KnowingRelationshipDescription> {
    private final PersonDAO personDAO;
    private final GraphDatabaseService graphDatabaseService;

    public KnowsConcurrentUpload(String schoolListPath, PersonDAO personDAO, BlockingQueue<KnowingRelationshipDescription> knowingRelationshipDescriptions, AtomicBoolean atomicBoolean, GraphDatabaseService graphDatabaseService) {
        super(schoolListPath, knowingRelationshipDescriptions, atomicBoolean, graphDatabaseService);
        this.personDAO = personDAO;
        this.graphDatabaseService = graphDatabaseService;
    }

    @Override
    protected SocialSupplier<KnowingRelationshipDescription> getSupplier() {
        return new SocialSupplier<KnowingRelationshipDescription>() {
            @Override
            public KnowingRelationshipDescription get(List<String> split) {
                return new KnowingRelationshipDescription(
                        personDAO.getIndexedNode(split.get(0)),
                        personDAO.getIndexedNode(split.get(1))
                );
            }
        };
    }
}
