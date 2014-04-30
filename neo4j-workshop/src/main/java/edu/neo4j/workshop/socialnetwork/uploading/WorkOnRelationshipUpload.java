package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import edu.neo4j.workshop.socialnetwork.dao.WorkCategoryDAO;
import edu.neo4j.workshop.socialnetwork.relations.PersonWorkRelationship;
import edu.neo4j.workshop.socialnetwork.relations.properties.PersonWorkExperience;

import java.util.List;

/**
 * @author partyks
 */
public class WorkOnRelationshipUpload extends AbstractUpload<WorkOnRelationshipDescription> {

    private final PersonDAO personDAO;
    private final WorkCategoryDAO categoryDAO;

    public WorkOnRelationshipUpload(String schoolListPath, PersonDAO personDAO, WorkCategoryDAO categoryDAO) {
        super(schoolListPath);
        this.personDAO = personDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    protected SocialSupplier<WorkOnRelationshipDescription> getSupplier() {
        return new SocialSupplier<WorkOnRelationshipDescription>() {
            @Override
            public WorkOnRelationshipDescription get(List<String> split) {
                return new WorkOnRelationshipDescription(
                        personDAO.getIndexedNode(split.get(0)),
                        categoryDAO.getIndexedNode(split.get(1)),
                        PersonWorkRelationship.EXPERIENCED,
                        PersonWorkExperience.of(Integer.parseInt(split.get(2)))
                );
            }
        };
    }
}
