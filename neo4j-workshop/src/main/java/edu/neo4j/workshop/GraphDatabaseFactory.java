package edu.neo4j.workshop;

import org.neo4j.graphdb.GraphDatabaseService;

import javax.annotation.PostConstruct;

/**
 * @author partyks
 */
public class GraphDatabaseFactory {
    private final String dbPath;
    private GraphDatabaseService graphDatabaseService;

    public GraphDatabaseFactory(String dbPath) {
        this.dbPath = dbPath;
    }

    @PostConstruct
    public void init() {
        graphDatabaseService = new org.neo4j.graphdb.factory.GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        registerShutdownHook(graphDatabaseService);
    }

    private void registerShutdownHook(final GraphDatabaseService graphDatabaseService) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                graphDatabaseService.shutdown();
            }
        }
        ));
    }

    public GraphDatabaseService get() {
        return graphDatabaseService;
    }
}
