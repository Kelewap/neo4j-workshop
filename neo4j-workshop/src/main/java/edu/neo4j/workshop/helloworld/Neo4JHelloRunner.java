package edu.neo4j.workshop.helloworld;

import edu.neo4j.workshop.socialnetwork.factories.PersonFactory;
import edu.neo4j.workshop.socialnetwork.factories.SchoolFactory;
import edu.neo4j.workshop.socialnetwork.factories.WorkCategoryFactory;
import edu.neo4j.workshop.socialnetwork.relations.PersonWorkRelationship;
import edu.neo4j.workshop.socialnetwork.relations.StudentSchoolRelationship;
import edu.neo4j.workshop.socialnetwork.relations.properties.PersonWorkExperience;
import edu.neo4j.workshop.socialnetwork.services.KnowRelationshipService;
import edu.neo4j.workshop.socialnetwork.services.PersonWorkRelationshipService;
import edu.neo4j.workshop.socialnetwork.services.StudentSchoolRelationshipService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final KnowRelationshipService knowRelationshipService;
    private final String dbPath;
    private final PersonFactory personFactory;
    private final SchoolFactory schoolFactory;
    private final StudentSchoolRelationshipService studentSchoolRelationshipService;
    private final WorkCategoryFactory workCategoryFactory;
    private final PersonWorkRelationshipService personWorkRelationshipService;

    @Autowired
    public Neo4JHelloRunner(GraphDatabaseService graphDatabaseService, KnowRelationshipService knowRelationshipService, @Value("${dbPath}") String dbPath, PersonFactory personFactory, SchoolFactory schoolFactory, StudentSchoolRelationshipService studentSchoolRelationshipService, WorkCategoryFactory workCategoryFactory, PersonWorkRelationshipService personWorkRelationshipService) {
        this.graphDatabaseService = graphDatabaseService;
        this.knowRelationshipService = knowRelationshipService;
        this.dbPath = dbPath;
        this.personFactory = personFactory;
        this.schoolFactory = schoolFactory;
        this.studentSchoolRelationshipService = studentSchoolRelationshipService;
        this.workCategoryFactory = workCategoryFactory;
        this.personWorkRelationshipService = personWorkRelationshipService;
    }

    public void createDb() throws IOException {
        try (Transaction transaction = graphDatabaseService.beginTx()) {
            final Node xiondz = personFactory.createPerson("Pawel Mikolajczyk", "xiondz");
            final Node bartek = personFactory.createPerson("Bartłomiej Mikolajczyk", "pawel.b");
            knowRelationshipService.associate(xiondz, bartek);

            final Node agh = schoolFactory.createSchool("AGH", "Akademia Górniczo Hutnicza w Krakowie");
            studentSchoolRelationshipService.associate(xiondz, agh, StudentSchoolRelationship.STUDYING);
            studentSchoolRelationshipService.associate(bartek, agh, StudentSchoolRelationship.TEACHING);

            final Node webapp = workCategoryFactory.createWorkCategory("webapp", "Aplikacje webowe");
            personWorkRelationshipService.associate(bartek, webapp, PersonWorkRelationship.BEGINNER, PersonWorkExperience.of(2));

            transaction.success();
        }
    }
}
