package edu.pg.formulaapp.classes.Team;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

/**
 * A class representing a DTO for creating and updating a team.
 */
public class TeamCreateUpdateDTO {
    @NotEmpty(message = "Team name cannot be null or empty")
    private String teamName;

    private List<String> driversIds;

    /**
     * Returns the name of the team.
     * @return the name of the team.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns the list of drivers associated with the team.
     * @return the list of drivers associated with the team.
     */
    public List<String> getDriversIds() {
        return driversIds;
    }

    /**
     * Sets the name of the team.
     * @param teamName the name of the team.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Sets the list of drivers associated with the team.
     * @param driversIds the list of drivers associated with the team.
     */
    public void setDriversIds(List<String> driversIds) {
        this.driversIds = driversIds;
    }
}
