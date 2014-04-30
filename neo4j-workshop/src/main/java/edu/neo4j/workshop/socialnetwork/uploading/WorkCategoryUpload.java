package edu.neo4j.workshop.socialnetwork.uploading;

import java.util.List;

/**
 * @author partyks
 */
public class WorkCategoryUpload extends AbstractUpload<WorkCategoryDescription> {
    public WorkCategoryUpload(String schoolListPath) {
        super(schoolListPath);
    }

    @Override
    protected SocialSupplier<WorkCategoryDescription> getSupplier() {
        return new SocialSupplier<WorkCategoryDescription>() {
            @Override
            public WorkCategoryDescription get(List<String> split) {
                return new WorkCategoryDescription(split.get(1), split.get(0));
            }
        };
    }
}
