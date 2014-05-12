package edu.neo4j.workshop.socialnetwork.loaders;

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

    @Autowired
    public ProjectLoader(ProjectUpload projectUpload, ProjectFactory projectFactory, PersonProjectUpload personProjectUpload, PersonProjectRelationshipService personProjectRelationshipService, ProjectCategoryUpload projectCategoryUpload, ProjectCategoryRelationshipService projectCategoryRelationshipService) {
        this.projectUpload = projectUpload;
        this.projectFactory = projectFactory;
        this.personProjectUpload = personProjectUpload;
        this.personProjectRelationshipService = personProjectRelationshipService;
        this.projectCategoryUpload = projectCategoryUpload;
        this.projectCategoryRelationshipService = projectCategoryRelationshipService;
    }

    public void loadProjects() throws IOException {
        final List<ProjectDescription> descriptions = projectUpload.retrieveDataFromFile();
        for (ProjectDescription description : descriptions) {
            projectFactory.createProject(description.getName(), description.getStartTime(), description.getEndTime());
        }
    }

    public void loadPeopleProjectsAssociations() throws IOException {
        final List<PersonProjectRelationshipDescription> knowingRelationshipDescriptions = personProjectUpload.retrieveDataFromFile();
        for (PersonProjectRelationshipDescription knowingRelationshipDescription : knowingRelationshipDescriptions) {
            personProjectRelationshipService.associate(knowingRelationshipDescription.getPerson(), knowingRelationshipDescription.getProject());
        }
    }

    public void loadProjectsCategoriesAssociations() throws IOException {
        final List<ProjectCategoryRelationshipDescription> personCategoryDescriptions = projectCategoryUpload.retrieveDataFromFile();
        for (ProjectCategoryRelationshipDescription personCategoryDescription : personCategoryDescriptions) {
            projectCategoryRelationshipService.associate(personCategoryDescription.getProject(), personCategoryDescription.getCategory());
        }
    }

}
