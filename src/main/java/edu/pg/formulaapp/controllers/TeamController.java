package edu.pg.formulaapp.controllers;
import org.springframework.web.bind.annotation.RestController;

import edu.pg.formulaapp.services.TeamService;
import edu.pg.formulaapp.classes.Driver.DriverCollectionDTO;
import edu.pg.formulaapp.classes.Team.Team;
import edu.pg.formulaapp.classes.Team.TeamCollectionDTO;
import edu.pg.formulaapp.classes.Team.TeamCreateUpdateDTO;
import edu.pg.formulaapp.classes.Team.TeamReadDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * TeamController is responsible for handling HTTP requests related to team operations.
 * It provides endpoints for creating, retrieving, updating, and deleting teams,
 * as well as retrieving drivers associated with a team.
 */
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    /**
     * The service handling team-related operations.
     */
    private final TeamService teamService;

    /**
     * Constructor for TeamController.
     * Injects the TeamService dependency.
     *
     * @param teamService the service handling team-related operations
     */
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Creates a new team.
     *
     * @param dto the data transfer object containing details of the team to be created
     * @return ResponseEntity with the created team's details and HTTP status CREATED (201)
     */
    @PostMapping
    public ResponseEntity<TeamReadDTO> createTeam(@RequestBody TeamCreateUpdateDTO dto) {
        TeamReadDTO createdTeam = teamService.createTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    /**
     * Retrieves a team by its ID.
     *
     * @param id the ID of the team to retrieve
     * @return ResponseEntity with the team's details and HTTP status OK (200)
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamReadDTO> getTeamById(@PathVariable UUID id) {
        Team team = teamService.getTeamByID(id.toString());

        TeamReadDTO teamDTO = new TeamReadDTO(team.getId().toString(), team.getTeamName(), team.getDrivers().stream()
                .map(driver -> driver.getName()) 
                .collect(Collectors.toList()));

        return ResponseEntity.ok(teamDTO);
    }

    /**
     * Retrieves all teams.
     *
     * @return ResponseEntity with a list of all teams and HTTP status OK (200)
     */
    @GetMapping
    public ResponseEntity<List<TeamCollectionDTO>> getAllTeams() {
        List<TeamCollectionDTO> teamDTOs = teamService.getAllTeams();

        return ResponseEntity.ok(teamDTOs);
    }

    /**
     * Updates a team by its ID.
     * @param teamName the name of the team to update
     * @param dto the data transfer object containing updated details of the team
     * @return ResponseEntity with the updated team's details and HTTP status OK (200)
     */
    @PutMapping("/name/{teamName}")
    public ResponseEntity<TeamReadDTO> updateTeam(@PathVariable String teamName, @RequestBody TeamCreateUpdateDTO dto) {
        TeamReadDTO updatedTeam = teamService.updateTeam(teamName, dto);
        return ResponseEntity.ok(updatedTeam);
    }

    /**
     * Deletes a team by its ID.
     *
     * @param id the ID of the team to delete
     * @return ResponseEntity with HTTP status NO_CONTENT (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a team by its name.
     *
     * @param teamName the name of the team to delete
     * @return ResponseEntity with HTTP status NO_CONTENT (204)
     */
    @DeleteMapping("/name/{teamName}")
    public ResponseEntity<Void> deleteTeamByName(@PathVariable String teamName) {
        System.out.println(teamName);
        teamService.deleteTeamByName(teamName);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all drivers associated with a team.
     *
     * @param id the ID of the team
     * @return ResponseEntity with a list of drivers and HTTP status OK (200)
     */
    @GetMapping("/{id}/drivers")
    public ResponseEntity<List<DriverCollectionDTO>> getDriversByTeam(@PathVariable UUID id) {
        List<DriverCollectionDTO> drivers = teamService.getDriversByTeam(id);
        return ResponseEntity.ok(drivers);
    }
}
