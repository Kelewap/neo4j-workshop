package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.helloworld.ChunkTransactionManager;
import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import edu.neo4j.workshop.socialnetwork.factories.PersonFactory;
import edu.neo4j.workshop.socialnetwork.services.KnowRelationshipService;
import edu.neo4j.workshop.socialnetwork.uploading.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author partyks
 */
@Component
public class PersonLoader {

    private final PeopleUpload peopleUpload;
    private final PersonFactory personFactory;
    private final KnowsUpload knowsUpload;
    private final KnowRelationshipService knowRelationshipService;
    private final ChunkTransactionManager chunkTransactionManager;
    private final PersonDAO personDAO;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    public PersonLoader(PeopleUpload peopleUpload, PersonFactory personFactory, KnowsUpload knowsUpload, KnowRelationshipService knowRelationshipService, ChunkTransactionManager chunkTransactionManager, PersonDAO personDAO) {
        this.peopleUpload = peopleUpload;
        this.personFactory = personFactory;
        this.knowsUpload = knowsUpload;
        this.knowRelationshipService = knowRelationshipService;
        this.chunkTransactionManager = chunkTransactionManager;
        this.personDAO = personDAO;
    }

    public void loadPeople() throws IOException {
        chunkTransactionManager.begin();
        final List<PersonDescription> descriptions = peopleUpload.retrieveDataFromFile();
        for (PersonDescription description : descriptions) {
            personFactory.createPerson(description.getName(), description.getUsername());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

    public void loadPeopleAssociations() throws IOException, InterruptedException {
        chunkTransactionManager.begin();
        System.out.println("Loading file");

        BlockingQueue<KnowingRelationshipDescription> knowingRelationshipDescriptions = new LinkedBlockingDeque<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        KnowsConcurrentUpload upload = new KnowsConcurrentUpload("knows.csv", personDAO, knowingRelationshipDescriptions, atomicBoolean);
        executor.submit(upload);
        while(!atomicBoolean.get()) {
            final KnowingRelationshipDescription poll = knowingRelationshipDescriptions.poll(10, TimeUnit.SECONDS);
            if (poll != null) {
                knowRelationshipService.associate(poll.getPerson1(), poll.getPerson2());
                chunkTransactionManager.bump();
                System.out.println("Loaded association...");
            }
        }
        chunkTransactionManager.commit();
    }

}
