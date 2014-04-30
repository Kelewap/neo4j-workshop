package edu.neo4j.workshop.socialnetwork.uploading;

import java.util.List;

/**
 * @author partyks
 */
public class SchoolUpload extends AbstractUpload<SchoolDescription> {

    public SchoolUpload(String schoolListPath) {
        super(schoolListPath);
    }

    @Override
    protected SocialSupplier<SchoolDescription> getSupplier() {
        return new SocialSupplier<SchoolDescription>() {
            @Override
            public SchoolDescription get(List<String> split) {
                return new SchoolDescription(split.get(1), split.get(0));
            }
        };
    }
}