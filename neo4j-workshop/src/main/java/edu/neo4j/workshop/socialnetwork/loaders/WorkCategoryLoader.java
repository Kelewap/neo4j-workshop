package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.socialnetwork.factories.WorkCategoryFactory;
import edu.neo4j.workshop.socialnetwork.services.PersonWorkRelationshipService;
import edu.neo4j.workshop.socialnetwork.uploading.WorkCategoryDescription;
import edu.neo4j.workshop.socialnetwork.uploading.WorkCategoryUpload;
import edu.neo4j.workshop.socialnetwork.uploading.WorkOnRelationshipDescription;
import edu.neo4j.workshop.socialnetwork.uploading.WorkOnRelationshipUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author partyks
 */
@Component
public class WorkCategoryLoader {

    private final WorkCategoryUpload workCategoryUpload;
    private final WorkCategoryFactory workCategoryFactory;
    private final PersonWorkRelationshipService personWorkRelationshipService;
    private final WorkOnRelationshipUpload workOnRelationshipUpload;

    @Autowired
    public WorkCategoryLoader(WorkCategoryUpload workCategoryUpload, WorkCategoryFactory workCategoryFactory, PersonWorkRelationshipService personWorkRelationshipService, WorkOnRelationshipUpload workOnRelationshipUpload) {
        this.workCategoryUpload = workCategoryUpload;
        this.workCategoryFactory = workCategoryFactory;
        this.personWorkRelationshipService = personWorkRelationshipService;
        this.workOnRelationshipUpload = workOnRelationshipUpload;
    }

    public void loadWorkCategories() throws IOException {
        final List<WorkCategoryDescription> workCategoryDescriptions = workCategoryUpload.retrieveDataFromFile();
        for (WorkCategoryDescription description : workCategoryDescriptions) {
            workCategoryFactory.createWorkCategory(description.getAbbreviation(), description.getName());
        }
    }

    public void loadWorkAssociations() throws IOException {
        final List<WorkOnRelationshipDescription> workOnRelationshipDescriptions = workOnRelationshipUpload.retrieveDataFromFile();
        for (WorkOnRelationshipDescription workOnRelationshipDescription : workOnRelationshipDescriptions) {
            personWorkRelationshipService.associate(workOnRelationshipDescription.getPerson(), workOnRelationshipDescription.getWorkCategory(), workOnRelationshipDescription.getRelationship(), workOnRelationshipDescription.getWorkExperience());
        }
    }

}
