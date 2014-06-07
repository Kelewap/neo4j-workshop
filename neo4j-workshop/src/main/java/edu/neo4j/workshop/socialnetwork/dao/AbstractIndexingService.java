package edu.neo4j.workshop.socialnetwork.dao;

import edu.neo4j.workshop.socialnetwork.factories.AbstractNodeFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 * @author partyks
 */
public class AbstractIndexingService {
    protected final GraphDatabaseService graphDatabaseService;
    protected final AbstractNodeFactory abstractNodeFactory;

    public AbstractIndexingService(GraphDatabaseService graphDatabaseService, AbstractNodeFactory abstractNodeFactory) {
        this.graphDatabaseService = graphDatabaseService;
        this.abstractNodeFactory = abstractNodeFactory;
    }

    public Node getIndexedNode(Object indexedProperty){
        System.out.println("In getIndexedNode");
        final Index<Node> index = graphDatabaseService.index().forNodes(abstractNodeFactory.getIndexName());
        System.out.println("Obtained index");

        Node ret = index.get(abstractNodeFactory.getIndexProperty(), indexedProperty).getSingle();
        System.out.println("Obtained value");
        return ret;
    }
}
