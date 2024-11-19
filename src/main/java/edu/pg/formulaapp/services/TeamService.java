package edu.pg.formulaapp.services;

import edu.pg.formulaapp.classes.Driver.Driver;
import edu.pg.formulaapp.classes.Driver.DriverCollectionDTO;
import edu.pg.formulaapp.classes.Team.Team;
import edu.pg.formulaapp.classes.Team.TeamCreateUpdateDTO;
import edu.pg.formulaapp.classes.Team.TeamReadDTO;
import edu.pg.formulaapp.classes.Team.TeamCollectionDTO;
import edu.pg.formulaapp.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import edu.pg.formulaapp.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final DriverRepository driverRepository;

    public TeamService(TeamRepository teamRepository, DriverRepository driverRepository) {
        this.teamRepository = teamRepository;
        this.driverRepository = driverRepository;
    }

    // Create a team
    public TeamReadDTO createTeam(TeamCreateUpdateDTO dto) {
        Team team = new Team();
        team.setTeamName(dto.getTeamName()); // Example, depending on the fields in DTO
        team = teamRepository.save(team);
        return new TeamReadDTO(team);
    }

    // Get team by ID
    public Team getTeamByID(String teamId) {
        return teamRepository.findById(UUID.fromString(teamId))
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }

    // Get team by name
    public Team getOrCreateTeamByName(String teamName) {
        // Próbujemy znaleźć zespół po nazwie
        Optional<Team> existingTeam = teamRepository.findByTeamName(teamName);
        
        if (existingTeam.isPresent()) {
            // Jeśli zespół już istnieje, zwróć go
            return existingTeam.get();
        } else {
            // Jeśli zespół nie istnieje, tworzymy nowy zespół
            Team newTeam = new Team();
            newTeam.setTeamName(teamName); // Ustawiamy nazwę zespołu
            newTeam = teamRepository.save(newTeam); // Zapisujemy zespół w bazie danych
            return newTeam; // Zwracamy nowo utworzony zespół
        }
    }
    

    // Get all teams
    public List<TeamCollectionDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        teams.forEach(team -> {
            System.out.println("Team: " + team.getTeamName());
            if (team.getDrivers() != null) {
                team.getDrivers().forEach(driver -> System.out.println("Driver: " + driver.getName()));
            }
        });
        return teams.stream()
                .map(team -> new TeamCollectionDTO(
                        team.getId().toString(),
                        team.getTeamName(),
                        team.getDrivers() != null ? team.getDrivers().stream()
                                .map(driver -> driver.getName())
                                .collect(Collectors.toList()) : List.of()
                ))
                .collect(Collectors.toList());
    }
    

    // Update a team
    public TeamReadDTO updateTeam(UUID id, TeamCreateUpdateDTO dto) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        existingTeam.setTeamName(dto.getTeamName());
        existingTeam = teamRepository.save(existingTeam);

        return new TeamReadDTO(existingTeam);
    }

    // Delete a team
    public void deleteTeam(UUID id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        teamRepository.delete(team);
    }

    public void deleteTeamByName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    
        teamRepository.delete(team);
    }

    // Get drivers by team
    public List<DriverCollectionDTO> getDriversByTeam(UUID teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new EntityNotFoundException("Team not found");
        }

        List<Driver> drivers = driverRepository.findByTeam(team.get());
        return drivers.stream()
                .map(driver -> new DriverCollectionDTO(driver.getId().toString(), driver.getName(), driver.getSurname()))
                .collect(Collectors.toList());
    }
}
