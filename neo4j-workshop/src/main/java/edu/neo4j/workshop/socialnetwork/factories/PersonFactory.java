package edu.neo4j.workshop.socialnetwork.factories;

import com.google.common.collect.ImmutableMap;
import edu.neo4j.workshop.socialnetwork.labels.SocialLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class PersonFactory extends AbstractNodeFactory {
    @Autowired
    public PersonFactory(GraphDatabaseService graphDatabaseService) {
        super(graphDatabaseService);
    }

    @Override
    protected org.neo4j.graphdb.Label getLabel() {
        return SocialLabel.PERSON;
    }

    public Node createPerson(String name, String username) {
        final Node node = createNode(ImmutableMap.<String, Object>of("username", username, "name", name));

        return indexNode(node);
    }

    @Override
    public String getIndexProperty() {
        return "username";
    }

    @Override
    public String getIndexName() {
        return "people";
    }

}
