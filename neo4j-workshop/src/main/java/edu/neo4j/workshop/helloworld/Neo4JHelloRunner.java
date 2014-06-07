package edu.neo4j.workshop.helloworld;

import edu.neo4j.workshop.socialnetwork.loaders.*;
import org.neo4j.graphdb.GraphDatabaseService;
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
    private final ProjectLoader projectLoader;

    @Autowired
    public Neo4JHelloRunner(GraphDatabaseService graphDatabaseService, PersonLoader personLoader, SchoolLoader schoolLoader, WorkCategoryLoader workCategoryLoader, LanguageLoader languageLoader, ProjectLoader projectLoader) {
        this.graphDatabaseService = graphDatabaseService;
        this.personLoader = personLoader;
        this.schoolLoader = schoolLoader;
        this.workCategoryLoader = workCategoryLoader;
        this.languageLoader = languageLoader;
        this.projectLoader = projectLoader;
    }

    public void createDb() throws IOException, InterruptedException {
//        try (Transaction transaction = graphDatabaseService.beginTx()) {
            System.out.println("Before person");
            personLoader.loadPeople();
            System.out.println("Person loaded");
            personLoader.loadPeopleAssociations();
            System.out.println("Associations loaded");
//
            schoolLoader.loadSchools();
            System.out.println("Schools loaded");
//            schoolLoader.loadSchoolAssociations();
//            System.out.println("Schools associations loaded");
//
//            workCategoryLoader.loadWorkCategories();
//            workCategoryLoader.loadWorkAssociations();
//            System.out.println("Work categories with ass loaded");
//
//            languageLoader.loadLanguages();
//            languageLoader.loadLearningRates();
//            System.out.println("Languages with ass loaded");
//
//            projectLoader.loadProjects();
//            projectLoader.loadPeopleProjectsAssociations();
//            projectLoader.loadProjectsCategoriesAssociations();
//            System.out.println("Projects loaded");
//
//            transaction.success();
//        }
    }
}
