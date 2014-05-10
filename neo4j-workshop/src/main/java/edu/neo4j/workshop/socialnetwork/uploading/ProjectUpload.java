package edu.neo4j.workshop.socialnetwork.uploading;

import java.sql.Date;
import java.util.List;

/**
 * @author partyks
 */
public class ProjectUpload extends AbstractUpload<ProjectDescription> {
    public ProjectUpload(String schoolListPath) {
        super(schoolListPath);
    }

    @Override
    protected SocialSupplier<ProjectDescription> getSupplier() {
        return new SocialSupplier<ProjectDescription>() {
            @Override
            public ProjectDescription get(List<String> split) {
                return new ProjectDescription(split.get(0), Date.valueOf(split.get(1)), Date.valueOf(split.get(2)));
            }
        };
    }

}
