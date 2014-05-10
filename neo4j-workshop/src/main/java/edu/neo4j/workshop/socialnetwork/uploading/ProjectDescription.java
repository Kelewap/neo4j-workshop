package edu.neo4j.workshop.socialnetwork.uploading;

import java.util.Date;

/**
 * @author partyks
 */
public class ProjectDescription {
    private final String name;
    private final Date startTime;
    private final Date endTime;

    public ProjectDescription(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
