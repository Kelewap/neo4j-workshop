package edu.neo4j.workshop.socialnetwork.relations.properties;

/**
 * @author partyks
 */
public class PersonWorkExperience {
    private final int yearsOfExperience;

    private PersonWorkExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public PersonWorkExperience addExperience(int years){
        return new PersonWorkExperience(yearsOfExperience + years);
    }

    public static PersonWorkExperience of(int i) {
        return new PersonWorkExperience(i);
    }
}
