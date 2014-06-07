package edu.neo4j.workshop.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Michal Partyka
 */
public class EmbeddedNeo4j {
    private static final String DB_PATH = "target/neo4j-hello-db";

    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Neo4JHelloRunner neo4JHelloRunner = (Neo4JHelloRunner) ctx.getBean("neo4JHelloRunner");
        neo4JHelloRunner.createDb();
    }

}
