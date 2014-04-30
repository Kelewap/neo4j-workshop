package edu.neo4j.workshop.socialnetwork.uploading;

import edu.neo4j.workshop.socialnetwork.dao.PersonDAO;
import edu.neo4j.workshop.socialnetwork.dao.SchoolDAO;
import edu.neo4j.workshop.socialnetwork.relations.StudentSchoolRelationship;

/**
 * @author partyks
 */
public class GraduatedRelationshipUploadImpl extends SchoolRelationshipUpload {
    public GraduatedRelationshipUploadImpl(String schoolListPath, PersonDAO personDAO, SchoolDAO schoolDAO) {
        super(schoolListPath, personDAO, schoolDAO);
    }

    @Override
    protected StudentSchoolRelationship getRelationshipType() {
        return StudentSchoolRelationship.GRADUATED;
    }
}
