package edu.neo4j.workshop.socialnetwork.factories;

import com.google.common.collect.ImmutableMap;
import edu.neo4j.workshop.socialnetwork.labels.SocialLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author partyks
 */
@Component
public class ProjectFactory extends AbstractNodeFactory {
    @Autowired
    public ProjectFactory(GraphDatabaseService graphDatabaseService) {
        super(graphDatabaseService);
    }

    @Override
    protected org.neo4j.graphdb.Label getLabel() {
        return SocialLabel.PROJECT;
    }

    public Node createProject(String name, Date startTime, Date endTime) {
        final Node node = createNode(ImmutableMap.<String, Object>of("name", name, "startTime", startTime.getTime(), "endTime", endTime.getTime()));

        return indexNode(node);
    }

    @Override
    public String getIndexProperty() {
        return "name";
    }

    @Override
    public String getIndexName() {
        return "projects";
    }

}
