package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.helloworld.ChunkTransactionManager;
import edu.neo4j.workshop.socialnetwork.factories.ProjectFactory;
import edu.neo4j.workshop.socialnetwork.services.PersonProjectRelationshipService;
import edu.neo4j.workshop.socialnetwork.services.ProjectCategoryRelationshipService;
import edu.neo4j.workshop.socialnetwork.uploading.PersonProjectUpload;
import edu.neo4j.workshop.socialnetwork.uploading.ProjectCategoryUpload;
import edu.neo4j.workshop.socialnetwork.uploading.ProjectDescription;
import edu.neo4j.workshop.socialnetwork.uploading.ProjectUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author partyks
 */
@Component
public class ProjectLoader {

    private final ProjectUpload projectUpload;
    private final ProjectFactory projectFactory;
    private final PersonProjectUpload personProjectUpload;
    private final PersonProjectRelationshipService personProjectRelationshipService;
    private final ProjectCategoryUpload projectCategoryUpload;
    private final ProjectCategoryRelationshipService projectCategoryRelationshipService;
    private final ChunkTransactionManager chunkTransactionManager;

    @Autowired
    public ProjectLoader(ProjectUpload projectUpload, ProjectFactory projectFactory, PersonProjectUpload personProjectUpload, PersonProjectRelationshipService personProjectRelationshipService, ProjectCategoryUpload projectCategoryUpload, ProjectCategoryRelationshipService projectCategoryRelationshipService, ChunkTransactionManager chunkTransactionManager) {
        this.projectUpload = projectUpload;
        this.projectFactory = projectFactory;
        this.personProjectUpload = personProjectUpload;
        this.personProjectRelationshipService = personProjectRelationshipService;
        this.projectCategoryUpload = projectCategoryUpload;
        this.projectCategoryRelationshipService = projectCategoryRelationshipService;
        this.chunkTransactionManager = chunkTransactionManager;
    }

    public void loadProjects() throws IOException {
        chunkTransactionManager.begin();
        final List<ProjectDescription> descriptions = projectUpload.retrieveDataFromFile(false);
        for (ProjectDescription description : descriptions) {
            projectFactory.createProject(description.getName(), description.getStartTime(), description.getEndTime());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

    public void loadPeopleProjectsAssociations() throws IOException {
        chunkTransactionManager.begin();
        final List<PersonProjectRelationshipDescription> knowingRelationshipDescriptions = personProjectUpload.retrieveDataFromFile(false);
        for (PersonProjectRelationshipDescription knowingRelationshipDescription : knowingRelationshipDescriptions) {
            personProjectRelationshipService.associate(knowingRelationshipDescription.getPerson(), knowingRelationshipDescription.getProject());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

    public void loadProjectsCategoriesAssociations() throws IOException {
        chunkTransactionManager.begin();
        final List<ProjectCategoryRelationshipDescription> personCategoryDescriptions = projectCategoryUpload.retrieveDataFromFile(false);
        for (ProjectCategoryRelationshipDescription personCategoryDescription : personCategoryDescriptions) {
            projectCategoryRelationshipService.associate(personCategoryDescription.getProject(), personCategoryDescription.getCategory());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

}
