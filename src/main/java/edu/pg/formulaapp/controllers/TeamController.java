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

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamReadDTO> createTeam(@RequestBody TeamCreateUpdateDTO dto) {
        TeamReadDTO createdTeam = teamService.createTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamReadDTO> getTeamById(@PathVariable UUID id) {
        // Get the Team entity from the service
        Team team = teamService.getTeamByID(id.toString());

        // Convert Team entity to TeamReadDTO
        TeamReadDTO teamDTO = new TeamReadDTO(team.getId().toString(), team.getTeamName(), team.getDrivers().stream()
                .map(driver -> driver.getName()) // Assuming you want the driver's names
                .collect(Collectors.toList()));

        // Return the DTO as a ResponseEntity
        return ResponseEntity.ok(teamDTO);
    }


    @GetMapping
    public ResponseEntity<List<TeamCollectionDTO>> getAllTeams() {
        // Pobierz wszystkie zespoły (już jako List<TeamCollectionDTO>)
        List<TeamCollectionDTO> teamDTOs = teamService.getAllTeams();

        return ResponseEntity.ok(teamDTOs);
    }



    @PutMapping("/{id}")
    public ResponseEntity<TeamReadDTO> updateTeam(@PathVariable UUID id, @RequestBody TeamCreateUpdateDTO dto) {
        TeamReadDTO updatedTeam = teamService.updateTeam(id, dto);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{teamName}")
    public ResponseEntity<Void> deleteTeamByName(@PathVariable String teamName) {
        System.out.println(teamName);
        teamService.deleteTeamByName(teamName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/drivers")
    public ResponseEntity<List<DriverCollectionDTO>> getDriversByTeam(@PathVariable UUID id) {
        List<DriverCollectionDTO> drivers = teamService.getDriversByTeam(id);
        return ResponseEntity.ok(drivers);
    }
}
