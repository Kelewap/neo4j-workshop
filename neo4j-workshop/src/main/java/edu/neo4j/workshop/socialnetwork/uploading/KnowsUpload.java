package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;

import java.util.List;

/**
 * @author partyks
 */
public class KnowsUpload extends AbstractUpload<KnowingRelationshipDescription> {
    private final PersonDAO personDAO;

    public KnowsUpload(String schoolListPath, PersonDAO personDAO) {
        super(schoolListPath);
        this.personDAO = personDAO;
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
