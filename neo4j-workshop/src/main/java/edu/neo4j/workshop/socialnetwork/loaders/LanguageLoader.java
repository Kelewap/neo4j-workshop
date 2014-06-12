package edu.neo4j.workshop.socialnetwork.loaders;

import edu.neo4j.workshop.helloworld.ChunkTransactionManager;
import edu.neo4j.workshop.socialnetwork.factories.LanguageFactory;
import edu.neo4j.workshop.socialnetwork.services.PersonLanguageRelationshipService;
import edu.neo4j.workshop.socialnetwork.uploading.LanguageDescription;
import edu.neo4j.workshop.socialnetwork.uploading.LanguageUpload;
import edu.neo4j.workshop.socialnetwork.uploading.PersonLanguageRelationshipDescription;
import edu.neo4j.workshop.socialnetwork.uploading.PersonLanguageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author partyks
 */
@Component
public class LanguageLoader {

    private final LanguageUpload languageUpload;
    private final LanguageFactory languageFactory;
    private final PersonLanguageUpload personLanguageUpload;
    private final PersonLanguageRelationshipService personLanguageRelationshipService;
    private final ChunkTransactionManager chunkTransactionManager;

    @Autowired
    public LanguageLoader(LanguageUpload languageUpload, LanguageFactory languageFactory, PersonLanguageUpload personLanguageUpload, PersonLanguageRelationshipService personLanguageRelationshipService, ChunkTransactionManager chunkTransactionManager) {
        this.languageUpload = languageUpload;
        this.languageFactory = languageFactory;
        this.personLanguageUpload = personLanguageUpload;
        this.personLanguageRelationshipService = personLanguageRelationshipService;
        this.chunkTransactionManager = chunkTransactionManager;
    }

    public void loadLanguages() throws IOException {
        chunkTransactionManager.begin();
        final List<LanguageDescription> languageDescriptions = languageUpload.retrieveDataFromFile(false);
        for (LanguageDescription description : languageDescriptions) {
            languageFactory.createLanguage(description.getShortcut(), description.getDescription());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }

    public void loadLearningRates() throws IOException {
        chunkTransactionManager.begin();
        final List<PersonLanguageRelationshipDescription> schoolRelationshipDescriptions = personLanguageUpload.retrieveDataFromFile(false);
        for (PersonLanguageRelationshipDescription schoolRelationshipDescription : schoolRelationshipDescriptions) {
            personLanguageRelationshipService.associate(schoolRelationshipDescription.getUsername(), schoolRelationshipDescription.getLanguage(), schoolRelationshipDescription.getLevel());
            chunkTransactionManager.bump();
        }
        chunkTransactionManager.commit();
    }
}
