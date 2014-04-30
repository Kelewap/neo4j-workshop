package edu.neo4j.workshop.socialnetwork.uploading;

import java.util.List;

/**
 * @author partyks
 */
public class PeopleUpload extends AbstractUpload<PersonDescription> {
    public PeopleUpload(String schoolListPath) {
        super(schoolListPath);
    }

    @Override
    protected SocialSupplier<PersonDescription> getSupplier() {
        return new SocialSupplier<PersonDescription>() {
            @Override
            public PersonDescription get(List<String> split) {
                return new PersonDescription(split.get(1) + split.get(2), split.get(0));
            }
        };
    }
}
