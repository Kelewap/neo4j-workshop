package edu.neo4j.workshop.socialnetwork.uploading;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author partyks
 */
public abstract class AbstractUpload<T> {
    private static final Logger LOGGER = Logger.getLogger(SchoolUpload.class.getName());
    private static final Splitter splitter = Splitter.on(',');
    protected final String schoolListPath;

    public AbstractUpload(String schoolListPath) {
        this.schoolListPath = schoolListPath;
    }

    public List<T> retrieveDataFromFile(boolean cut) throws IOException {
        List<T> descriptions = new ArrayList<>();
        int counter = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(schoolListPath))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                final List<String> split = splitter.splitToList(line);
                descriptions.add(
                        getSupplier().get(split)
                );
                counter++;
                if (counter == 2000000) {
                    System.out.println("2000000 added");
                    if (cut) {
                        return descriptions;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found, unfortunetly :(", e);
            throw e;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with IO", e);
            throw e;
        }
        return descriptions;
    }

    protected abstract SocialSupplier<T> getSupplier();
}
