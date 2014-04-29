package edu.neo4j.workshop.socialnetwork.factories;

import edu.neo4j.workshop.socialnetwork.NotUniqueException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import java.util.Map;

/**
 * @author partyks
 */
public abstract class AbstractNodeFactory {
    protected final GraphDatabaseService graphDatabaseService;

    public AbstractNodeFactory(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
    }

    protected Node indexNode(Node node) {
        final Index<Node> people = graphDatabaseService.index().forNodes(getIndexName());
        final Node alreadyIndexed = people.putIfAbsent(node, getIndexProperty(), node.getProperty(getIndexProperty()));
        if (alreadyIndexed != null) {
            throw new NotUniqueException(node.getProperty(getIndexProperty()) + " must be unique");
        }
        return node;
    }

    protected Node createNode(Map<String, Object> objects) {
        final Node node = graphDatabaseService.createNode();
        for (Map.Entry<String, Object> stringObjectEntry : objects.entrySet()) {
            node.setProperty(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }
        return node;
    }

    public abstract String getIndexProperty();

    public abstract String getIndexName();


}
