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
public class LanguageFactory extends AbstractNodeFactory {
    @Autowired
    public LanguageFactory(GraphDatabaseService graphDatabaseService) {
        super(graphDatabaseService);
    }

    @Override
    protected org.neo4j.graphdb.Label getLabel() {
        return SocialLabel.LANGUAGE;
    }

    public Node createLanguage(String abbreviation, String name) {
        final Node node = createNode(ImmutableMap.<String, Object>of("shortcut", abbreviation, "name", name));

        return indexNode(node);
    }

    @Override
    public String getIndexProperty() {
        return "shortcut";
    }

    @Override
    public String getIndexName() {
        return "languages";
    }

}
