package edu.neo4j.workshop.socialnetwork.uploading;

import java.util.List;

/**
 * @author partyks
 */
public class LanguageUpload extends AbstractUpload<LanguageDescription> {

    public LanguageUpload(String schoolListPath) {
        super(schoolListPath);
    }

    @Override
    protected SocialSupplier<LanguageDescription> getSupplier() {
        return new SocialSupplier<LanguageDescription>() {
            @Override
            public LanguageDescription get(List<String> split) {
                return new LanguageDescription(split.get(0), split.get(1));
            }
        };
    }
}