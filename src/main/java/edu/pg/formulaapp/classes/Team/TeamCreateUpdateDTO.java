package edu.pg.formulaapp.classes.Team;

import java.util.List;

import edu.pg.formulaapp.classes.Driver.DriverCreateUpdateDTO;
import jakarta.validation.constraints.NotEmpty;

/**
 * A class representing a DTO for creating and updating a team.
 */
public class TeamCreateUpdateDTO {
    /**
     * The name of the team.
     */
    @NotEmpty(message = "Team name cannot be null or empty")
    private String teamName;

    /**
     * The list of drivers associated with the team.
     */
    private List<DriverCreateUpdateDTO> drivers;

    /**
     * Constructs a new TeamCreateUpdateDTO instance.
     */
    public TeamCreateUpdateDTO() {}
    
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
    public List<DriverCreateUpdateDTO> getDrivers() {
        return drivers;
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
     * @param drivers the list of drivers associated with the team.
     */
    public void setDrivers(List<DriverCreateUpdateDTO> drivers) {
        this.drivers = drivers;
    }
}
