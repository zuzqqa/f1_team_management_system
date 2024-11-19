package edu.pg.formulaapp.classes.Team;

import java.util.List;

public class TeamCollectionDTO {
    private String id;
    private String teamName;
    private List<String> driverNames; 

    /**
     * Constructor for TeamCollectionDTO class.
     * @param id the id of the team.
     * @param teamName the name of the team.
     * @param driverNames the names of the drivers in the team.
     */
    public TeamCollectionDTO(String id, String teamName, List<String> driverNames) {
        this.id = id;
        this.teamName = teamName;
        this.driverNames = driverNames;
    }

    /**
     * Function getId() returns the id of the team.
     * @return the id of the team.
     */
    public String getId() {
        return id;
    }

    /**
     * Function getTeamName() returns the name of the team.
     * @return the name of the team.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Function getDriverNames() returns the names of the drivers in the team.
     * @return the names of the drivers in the team.
     */
    public List<String> getDriverNames() {
        return driverNames;
    }

    /**
     * Function setId() sets the id of the team.
     * @param id the id of the team.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Function setTeamName() sets the name of the team.
     * @param teamName the name of the team.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Function setDriverNames() sets the names of the drivers in the team.
     * @param driverNames the names of the drivers in the team.
     */
    public void setDriverNames(List<String> driverNames) {
        this.driverNames = driverNames;
    }

    /**
     * Function toString() returns the string representation of the team.
     * @return the string representation of the team.
     */
    @Override
    public String toString() {
        return "TeamCollectionDTO{" +
                "id='" + id + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
