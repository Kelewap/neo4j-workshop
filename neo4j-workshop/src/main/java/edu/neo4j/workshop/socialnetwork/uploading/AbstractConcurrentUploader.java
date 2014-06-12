package edu.neo4j.workshop.socialnetwork.uploading;

import com.google.common.base.Splitter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author partyks
 */
public abstract class AbstractConcurrentUploader<T> implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(SchoolUpload.class.getName());
    private static final Splitter splitter = Splitter.on(',');
    protected final String schoolListPath;
    private final BlockingQueue<T> blockingQueue;
    private final AtomicBoolean isDone;
    private final GraphDatabaseService graphDatabaseService;

    public AbstractConcurrentUploader(String schoolListPath, BlockingQueue<T> blockingQueue, AtomicBoolean atomicBoolean, GraphDatabaseService graphDatabaseService) {
        this.schoolListPath = schoolListPath;
        this.blockingQueue = blockingQueue;
        this.isDone = atomicBoolean;
        this.graphDatabaseService = graphDatabaseService;
    }

    public void run() {
        final Transaction transaction = graphDatabaseService.beginTx();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(schoolListPath))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                final List<String> split = splitter.splitToList(line);
                blockingQueue.put(
                        getSupplier().get(split)
                );
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found, unfortunetly :(", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with IO", e);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "interrupted", e);
        }
        transaction.success();
        transaction.close();
        this.isDone.set(true);
    }

    protected abstract SocialSupplier<T> getSupplier();
}
