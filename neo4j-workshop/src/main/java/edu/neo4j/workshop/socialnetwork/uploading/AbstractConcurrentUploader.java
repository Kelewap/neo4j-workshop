package edu.neo4j.workshop.socialnetwork.uploading;

import com.google.common.base.Splitter;

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

    public AbstractConcurrentUploader(String schoolListPath, BlockingQueue<T> blockingQueue, AtomicBoolean atomicBoolean) {
        this.schoolListPath = schoolListPath;
        this.blockingQueue = blockingQueue;
        this.isDone = atomicBoolean;
    }

    public void run() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(schoolListPath))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                final List<String> split = splitter.splitToList(line);
                blockingQueue.add(
                        getSupplier().get(split)
                );
                System.out.println("Added to queue...!");
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found, unfortunetly :(", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with IO", e);
        }
        this.isDone.set(true);
    }

    protected abstract SocialSupplier<T> getSupplier();
}
