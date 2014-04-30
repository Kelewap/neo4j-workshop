package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.socialnetwork.factories.SchoolFactory;
import edu.neo4j.workshop.socialnetwork.services.StudentSchoolRelationshipService;
import edu.neo4j.workshop.socialnetwork.uploading.SchoolDescription;
import edu.neo4j.workshop.socialnetwork.uploading.SchoolRelationshipDescription;
import edu.neo4j.workshop.socialnetwork.uploading.SchoolRelationshipUpload;
import edu.neo4j.workshop.socialnetwork.uploading.SchoolUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author partyks
 */
@Component
public class SchoolLoader {

    private final SchoolUpload schoolUpload;
    private final SchoolFactory schoolFactory;
    private final SchoolRelationshipUpload studyingRelationshipUpload;
    private final SchoolRelationshipUpload graduatedRelationshipUpload;
    private final StudentSchoolRelationshipService studentSchoolRelationshipService;

    @Autowired
    public SchoolLoader(SchoolUpload schoolUpload, SchoolFactory schoolFactory, SchoolRelationshipUpload studyingRelationshipUpload, SchoolRelationshipUpload graduatedRelationshipUpload, StudentSchoolRelationshipService studentSchoolRelationshipService) {
        this.schoolUpload = schoolUpload;
        this.schoolFactory = schoolFactory;
        this.studyingRelationshipUpload = studyingRelationshipUpload;
        this.graduatedRelationshipUpload = graduatedRelationshipUpload;
        this.studentSchoolRelationshipService = studentSchoolRelationshipService;
    }

    public void loadSchools() throws IOException {
        final List<SchoolDescription> schoolDescriptions = schoolUpload.retrieveDataFromFile();
        for (SchoolDescription description : schoolDescriptions) {
            schoolFactory.createSchool(description.getAbbreviation(), description.getName());
        }    }

    public void loadSchoolAssociations() throws IOException {
        final List<SchoolRelationshipDescription> schoolRelationshipDescriptions = studyingRelationshipUpload.retrieveDataFromFile();
        for (SchoolRelationshipDescription schoolRelationshipDescription : schoolRelationshipDescriptions) {
            studentSchoolRelationshipService.associate(schoolRelationshipDescription.getPerson(), schoolRelationshipDescription.getSchool(), schoolRelationshipDescription.getRelationship());
        }

        final List<SchoolRelationshipDescription> schoolRelationshipDescriptions1 = graduatedRelationshipUpload.retrieveDataFromFile();
        for (SchoolRelationshipDescription schoolRelationshipDescription : schoolRelationshipDescriptions1) {
            studentSchoolRelationshipService.associate(schoolRelationshipDescription.getPerson(), schoolRelationshipDescription.getSchool(), schoolRelationshipDescription.getRelationship());
        }
    }

}
