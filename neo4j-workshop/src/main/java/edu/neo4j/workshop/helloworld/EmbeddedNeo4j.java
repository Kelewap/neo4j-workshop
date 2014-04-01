package edu.neo4j.workshop.helloworld;

import java.io.IOException;

/**
 * @author Michal Partyka
 */
public class EmbeddedNeo4j {
    private static final String DB_PATH = "target/neo4j-hello-db";

    public static void main(String[] args) throws IOException {
        Neo4JHelloRunner neo4JHelloRunner = new Neo4JHelloRunner(DB_PATH);
        neo4JHelloRunner.createDb();
    }

}
