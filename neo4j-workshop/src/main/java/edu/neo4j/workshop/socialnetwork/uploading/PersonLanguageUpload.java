package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.LanguageDAO;
import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import edu.neo4j.workshop.socialnetwork.relations.LaungageRelationship;

import java.util.List;

/**
 * @author partyks
 */
public class PersonLanguageUpload extends AbstractUpload<PersonLanguageRelationshipDescription> {

    private final PersonDAO personDAO;
    private final LanguageDAO languageDAO;

    public PersonLanguageUpload(String personLanguageFilePath, PersonDAO personDAO, LanguageDAO languageDAO) {
        super(personLanguageFilePath);
        this.personDAO = personDAO;
        this.languageDAO = languageDAO;
    }

    @Override
    protected SocialSupplier<PersonLanguageRelationshipDescription> getSupplier() {
        return new SocialSupplier<PersonLanguageRelationshipDescription>() {
            @Override
            public PersonLanguageRelationshipDescription get(List<String> split) {
                return new PersonLanguageRelationshipDescription(
                        personDAO.getIndexedNode(split.get(0)),
                        languageDAO.getIndexedNode(split.get(1)),
                        LaungageRelationship.valueOf(split.get(2).toUpperCase())
                );
            }
        };
    }
}
