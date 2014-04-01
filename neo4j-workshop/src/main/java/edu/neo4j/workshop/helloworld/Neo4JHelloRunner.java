package edu.neo4j.workshop.helloworld;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author partyks
 */
public class Neo4JHelloRunner {
    private static final Logger LOGGER = Logger.getLogger(Neo4JHelloRunner.class.getName());
    private final String dbPath;
    private final File db;

    public Neo4JHelloRunner(String dbPath) {
        this.dbPath = dbPath;
        db = new File(dbPath);
    }

    public void createDb() throws IOException {
        clearDb();

        GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
        registerShutdownHook(graphDatabaseService);

        try (Transaction transaction = graphDatabaseService.beginTx()) {
            final Node firstNode = graphDatabaseService.createNode();
            firstNode.setProperty("name", "Kelewap ");
            final Node secondNode = graphDatabaseService.createNode();
            secondNode.setProperty("name", " partyks");
            final Relationship hatingRelationship = firstNode.createRelationshipTo(secondNode, Relation.HATES);
            hatingRelationship.setProperty("why", "because he is an idiot!");

            LOGGER.log(Level.INFO, firstNode.getProperty("name") + hatingRelationship.getType().name() + secondNode.getProperty("name"));
            LOGGER.log(Level.INFO, "Why is that? \n>>> " + hatingRelationship.getProperty("why"));

            transaction.success();
        }
    }

    private void clearDb() throws IOException {
        FileUtils.deleteRecursively(db);
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
}
