package edu.neo4j.workshop.helloworld;

import edu.neo4j.workshop.socialnetwork.loaders.LanguageLoader;
import edu.neo4j.workshop.socialnetwork.loaders.PersonLoader;
import edu.neo4j.workshop.socialnetwork.loaders.SchoolLoader;
import edu.neo4j.workshop.socialnetwork.loaders.WorkCategoryLoader;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author partyks
 */
@Component
public class Neo4JHelloRunner {
    private static final Logger LOGGER = Logger.getLogger(Neo4JHelloRunner.class.getName());
    private final GraphDatabaseService graphDatabaseService;
    private final PersonLoader personLoader;
    private final SchoolLoader schoolLoader;
    private final WorkCategoryLoader workCategoryLoader;
    private final LanguageLoader languageLoader;

    @Autowired
    public Neo4JHelloRunner(GraphDatabaseService graphDatabaseService, PersonLoader personLoader, SchoolLoader schoolLoader, WorkCategoryLoader workCategoryLoader, LanguageLoader languageLoader) {
        this.graphDatabaseService = graphDatabaseService;
        this.personLoader = personLoader;
        this.schoolLoader = schoolLoader;
        this.workCategoryLoader = workCategoryLoader;
        this.languageLoader = languageLoader;
    }

    public void createDb() throws IOException {
        try (Transaction transaction = graphDatabaseService.beginTx()) {
            personLoader.loadPeople();
            personLoader.loadPeopleAssociations();

            schoolLoader.loadSchools();
            schoolLoader.loadSchoolAssociations();

            workCategoryLoader.loadWorkCategories();
            workCategoryLoader.loadWorkAssociations();

            languageLoader.loadLanguages();
            languageLoader.loadLearningRates();

            transaction.success();
        }
    }
}
