package edu.neo4j.workshop.socialnetwork.loaders;

import org.neo4j.graphdb.Node;

/**
 * @author partyks
 */
public class ProjectCategoryRelationshipDescription {
    private final Node project;
    private final Node category;

    public ProjectCategoryRelationshipDescription(Node project, Node category) {
        this.project = project;
        this.category = category;
    }

    public Node getProject() {
        return project;
    }

    public Node getCategory() {
        return category;
    }
}
