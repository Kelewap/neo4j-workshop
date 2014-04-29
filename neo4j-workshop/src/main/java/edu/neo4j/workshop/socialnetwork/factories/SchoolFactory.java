package edu.neo4j.workshop.socialnetwork.factories;

import com.google.common.collect.ImmutableMap;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class SchoolFactory extends AbstractNodeFactory {

    @Autowired
    public SchoolFactory(GraphDatabaseService graphDatabaseService) {
        super(graphDatabaseService);
    }

    public Node createSchool(String abbreviation, String name) {
        final Node node = createNode(ImmutableMap.<String, Object>of("abbreviation", abbreviation, "name", name));
        return indexNode(node);
    }

    @Override
    public String getIndexProperty() {
        return "abbreviation";
    }

    @Override
    public String getIndexName() {
        return "schools";
    }
}
