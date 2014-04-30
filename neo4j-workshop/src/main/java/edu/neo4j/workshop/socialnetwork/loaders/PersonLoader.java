package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.socialnetwork.factories.PersonFactory;
import edu.neo4j.workshop.socialnetwork.services.KnowRelationshipService;
import edu.neo4j.workshop.socialnetwork.uploading.KnowingRelationshipDescription;
import edu.neo4j.workshop.socialnetwork.uploading.KnowsUpload;
import edu.neo4j.workshop.socialnetwork.uploading.PeopleUpload;
import edu.neo4j.workshop.socialnetwork.uploading.PersonDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author partyks
 */
@Component
public class PersonLoader {

    private final PeopleUpload peopleUpload;
    private final PersonFactory personFactory;
    private final KnowsUpload knowsUpload;
    private final KnowRelationshipService knowRelationshipService;

    @Autowired
    public PersonLoader(PeopleUpload peopleUpload, PersonFactory personFactory, KnowsUpload knowsUpload, KnowRelationshipService knowRelationshipService) {
        this.peopleUpload = peopleUpload;
        this.personFactory = personFactory;
        this.knowsUpload = knowsUpload;
        this.knowRelationshipService = knowRelationshipService;
    }

    public void loadPeople() throws IOException {
        final List<PersonDescription> descriptions = peopleUpload.retrieveDataFromFile();
        for (PersonDescription description : descriptions) {
            personFactory.createPerson(description.getName(), description.getUsername());
        }
    }

    public void loadPeopleAssociations() throws IOException {
        final List<KnowingRelationshipDescription> knowingRelationshipDescriptions = knowsUpload.retrieveDataFromFile();
        for (KnowingRelationshipDescription knowingRelationshipDescription : knowingRelationshipDescriptions) {
            knowRelationshipService.associate(knowingRelationshipDescription.getPerson1(), knowingRelationshipDescription.getPerson2());
        }
    }

}
