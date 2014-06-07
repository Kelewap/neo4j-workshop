package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author partyks
 */
public class KnowsConcurrentUpload extends AbstractConcurrentUploader<KnowingRelationshipDescription> {
    private final PersonDAO personDAO;

    public KnowsConcurrentUpload(String schoolListPath, PersonDAO personDAO, BlockingQueue<KnowingRelationshipDescription> knowingRelationshipDescriptions, AtomicBoolean atomicBoolean) {
        super(schoolListPath, knowingRelationshipDescriptions, atomicBoolean);
        this.personDAO = personDAO;
    }

    @Override
    protected SocialSupplier<KnowingRelationshipDescription> getSupplier() {
        System.out.println("Before going to DAO...");
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
