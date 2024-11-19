package edu.pg.formulaapp.classes.Team;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class representing a DTO for Team entity.
 */
public class TeamReadDTO {
    public String id;
    public String teamName;
    private List<String> driversNames;
    
    /**
     * Constructs a new TeamReadDTO instance with the specified attributes.
     * @param id 
     * @param teamName 
     * @param driversNames
     */
    public TeamReadDTO(String id, String teamName, List<String> driversNames) {
        this.id = id;
        this.teamName = teamName;
        this.driversNames = driversNames;
    }

    /**
     * Constructs a new TeamReadDTO from a Team entity.
     * @param team the Team entity to convert to a DTO.
     */
    public TeamReadDTO(Team team) {
        this.id = team.getId().toString(); // Assuming `getId()` returns UUID
        this.teamName = team.getTeamName(); // Assuming `getTeamName()` exists
        this.driversNames = team.getDrivers().stream() // Assuming `getDrivers()` returns a list of Driver objects
                                  .map(driver -> driver.getName())  // Assuming `getName()` returns the driver's name
                                  .collect(Collectors.toList());
    }

    /**
     * Returns the id of the team.
     * @return the id of the team.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the team.
     * @return the name of the team.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns the names of the drivers in the team.
     * @return the names of the drivers in the team.
     */
    public List<String> getDriverNames() {
        return driversNames;
    }

    /**
     * Sets the id of the team.
     * @param id the id of the team.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the name of the team.
     * @param teamName the name of the team.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    /**
     * Sets the names of the drivers in the team.
     * @param driversNames the names of the drivers in the team.
     */
    public void setDriverNames(List<String> driversNames) {
        this.driversNames = driversNames;
    }

    /**
     * Returns the string representation of the TeamReadDTO instance.
     * @return the string representation of the TeamReadDTO instance.
     */
    @Override
    public String toString() {
        return "TeamReadDTO{" +
                "id='" + id + '\'' +
                ", teamName='" + teamName + '\'' +
                ", driversNames=" + driversNames +
                '}';
    }
}
