package edu.neo4j.workshop.helloworld;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author partyks
 */
@Component
public class ChunkTransactionManager {
    private final int CHUNK_SIZE;
    private final GraphDatabaseService graphDatabaseService;
    private Transaction currentTransaction;
    private int bumpsNumber;

    @Autowired
    public ChunkTransactionManager(@Value("${chunk.size}") int chunk_size, GraphDatabaseService graphDatabaseService) {
        CHUNK_SIZE = chunk_size;
        this.graphDatabaseService = graphDatabaseService;
    }

    public void begin() {
        currentTransaction = graphDatabaseService.beginTx();
    }

    public void bump() {
        bumpsNumber++;
        if (bumpsNumber == CHUNK_SIZE) {
            commitAndNew();
            System.out.println("Chunk of : " + CHUNK_SIZE + " loaded!");
        }
    }

    private void commitAndNew() {
        bumpsNumber = 0;
        currentTransaction.success();
        currentTransaction.close();
        currentTransaction = graphDatabaseService.beginTx();
    }

    public void commit() {
        currentTransaction.success();
        currentTransaction.close();
        currentTransaction = null;
        bumpsNumber = 0;
    }


}
