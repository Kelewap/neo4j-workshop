package edu.neo4j.workshop.socialnetwork.relations;

import org.neo4j.graphdb.RelationshipType;

/**
 * @author partyks
 */
public enum  StudentSchoolRelationship implements RelationshipType {
    STUDYING, GRADUATED, TEACHING
}
