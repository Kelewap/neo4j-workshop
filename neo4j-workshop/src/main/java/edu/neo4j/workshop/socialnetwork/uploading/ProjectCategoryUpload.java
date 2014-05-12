package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.ProjectDAO;
import edu.neo4j.workshop.socialnetwork.dao.WorkCategoryDAO;
import edu.neo4j.workshop.socialnetwork.loaders.ProjectCategoryRelationshipDescription;

import java.util.List;

/**
 * @author partyks
 */
public class ProjectCategoryUpload extends AbstractUpload<ProjectCategoryRelationshipDescription> {

    private final ProjectDAO projectDAO;
    private final WorkCategoryDAO categoryDAO;

    public ProjectCategoryUpload(String personLanguageFilePath, ProjectDAO projectDAO, WorkCategoryDAO categoryDAO) {
        super(personLanguageFilePath);
        this.projectDAO = projectDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    protected SocialSupplier<ProjectCategoryRelationshipDescription> getSupplier() {
        return new SocialSupplier<ProjectCategoryRelationshipDescription>() {
            @Override
            public ProjectCategoryRelationshipDescription get(List<String> split) {
                return new ProjectCategoryRelationshipDescription(
                        categoryDAO.getIndexedNode(split.get(1)),
                        projectDAO.getIndexedNode(split.get(0))
                );
            }
        };
    }
}
