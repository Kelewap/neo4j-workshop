package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import edu.neo4j.workshop.socialnetwork.dao.ProjectDAO;
import edu.neo4j.workshop.socialnetwork.loaders.PersonProjectRelationshipDescription;

import java.util.List;

/**
 * @author partyks
 */
public class PersonProjectUpload extends AbstractUpload<PersonProjectRelationshipDescription> {

    private final ProjectDAO projectDAO;
    private final PersonDAO personDAO;

    public PersonProjectUpload(String personLanguageFilePath, ProjectDAO projectDAO, PersonDAO personDAO) {
        super(personLanguageFilePath);
        this.projectDAO = projectDAO;
        this.personDAO = personDAO;
    }

    @Override
    protected SocialSupplier<PersonProjectRelationshipDescription> getSupplier() {
        return new SocialSupplier<PersonProjectRelationshipDescription>() {
            @Override
            public PersonProjectRelationshipDescription get(List<String> split) {
                return new PersonProjectRelationshipDescription(
                        personDAO.getIndexedNode(split.get(1)),
                        projectDAO.getIndexedNode(split.get(0))
                );
            }
        };
    }
}
