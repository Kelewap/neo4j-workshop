package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.helloworld.ChunkTransactionManager;
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
    private final ChunkTransactionManager chunkTransactionManager;

    @Autowired
    public WorkCategoryLoader(WorkCategoryUpload workCategoryUpload, WorkCategoryFactory workCategoryFactory, PersonWorkRelationshipService personWorkRelationshipService, WorkOnRelationshipUpload workOnRelationshipUpload, ChunkTransactionManager chunkTransactionManager) {
        this.workCategoryUpload = workCategoryUpload;
        this.workCategoryFactory = workCategoryFactory;
        this.personWorkRelationshipService = personWorkRelationshipService;
        this.workOnRelationshipUpload = workOnRelationshipUpload;
        this.chunkTransactionManager = chunkTransactionManager;
    }

    public void loadWorkCategories() throws IOException {
        chunkTransactionManager.begin();
        final List<WorkCategoryDescription> workCategoryDescriptions = workCategoryUpload.retrieveDataFromFile(false);
        for (WorkCategoryDescription description : workCategoryDescriptions) {
            workCategoryFactory.createWorkCategory(description.getAbbreviation(), description.getName());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

    public void loadWorkAssociations() throws IOException {
        chunkTransactionManager.begin();
        final List<WorkOnRelationshipDescription> workOnRelationshipDescriptions = workOnRelationshipUpload.retrieveDataFromFile(false);
        for (WorkOnRelationshipDescription workOnRelationshipDescription : workOnRelationshipDescriptions) {
            personWorkRelationshipService.associate(workOnRelationshipDescription.getPerson(), workOnRelationshipDescription.getWorkCategory(), workOnRelationshipDescription.getRelationship(), workOnRelationshipDescription.getWorkExperience());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

}
