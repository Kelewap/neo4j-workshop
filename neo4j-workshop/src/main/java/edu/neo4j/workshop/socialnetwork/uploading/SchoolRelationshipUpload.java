package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import edu.neo4j.workshop.socialnetwork.dao.SchoolDAO;
import edu.neo4j.workshop.socialnetwork.relations.StudentSchoolRelationship;

import java.util.List;

/**
 * @author partyks
 */
public abstract class SchoolRelationshipUpload extends AbstractUpload<SchoolRelationshipDescription>{
    private final PersonDAO personDAO;
    private final SchoolDAO schoolDAO;

    public SchoolRelationshipUpload(String schoolListPath, PersonDAO personDAO, SchoolDAO schoolDAO) {
        super(schoolListPath);
        this.personDAO = personDAO;
        this.schoolDAO = schoolDAO;
    }

    @Override
    protected SocialSupplier<SchoolRelationshipDescription> getSupplier() {
        return new SocialSupplier<SchoolRelationshipDescription>() {
            @Override
            public SchoolRelationshipDescription get(List<String> split) {
                return new SchoolRelationshipDescription(
                        personDAO.getIndexedNode(split.get(0)),
                        schoolDAO.getIndexedNode(split.get(1)),
                        getRelationshipType()
                );
            }
        };
    }

    protected abstract StudentSchoolRelationship getRelationshipType();
}
