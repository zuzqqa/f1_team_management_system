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

/**
 * Service class for managing operations related to teams.
 * This service interacts with the repository layer to perform CRUD operations on teams,
 * handles business logic, and transforms entities to DTOs.
 */
@Service
public class TeamService {
    /**
     * The repository handling team-related operations.
     */
    private final TeamRepository teamRepository;

    /**
     * The repository handling driver-related operations.
     */
    private final DriverRepository driverRepository;

    /**
     * Constructor for TeamService.
     * Injects the TeamRepository and DriverRepository dependencies.
     *
     * @param teamRepository the repository handling team-related operations
     * @param driverRepository the repository handling driver-related operations
     */
    public TeamService(TeamRepository teamRepository, DriverRepository driverRepository) {
        this.teamRepository = teamRepository;
        this.driverRepository = driverRepository;
    }

    /**
     * Creates a new team.
     *
     * @param dto the data transfer object containing details of the team to be created
     * @return the created team's details
     */
    public TeamReadDTO createTeam(TeamCreateUpdateDTO dto) {
        Team team = new Team();
        team.setTeamName(dto.getTeamName()); 
        team = teamRepository.save(team);
        return new TeamReadDTO(team);
    }

    /**
     * Retrieves a team by its ID.
     *
     * @param teamId The UUID of the team to retrieve as a string.
     * @return The team entity.
     * @throws EntityNotFoundException if the team is not found.
     */
    public Team getTeamByID(String teamId) {
        return teamRepository.findById(UUID.fromString(teamId))
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }

    /**
     * Retrieves a team by its name. If the team does not exist, creates a new team.
     *
     * @param teamName The name of the team to retrieve or create.
     * @return The existing or newly created team entity.
     */
    public Team getOrCreateTeamByName(String teamName) {
        Optional<Team> existingTeam = teamRepository.findByTeamName(teamName);
        
        if (existingTeam.isPresent()) {
            return existingTeam.get();
        } else {
            Team newTeam = new Team();
            newTeam.setTeamName(teamName);
            newTeam = teamRepository.save(newTeam); 
            return newTeam; 
        }
    }
    
    /**
     * Retrieves all teams.
     *
     * @return A list of all teams with basic information (name and drivers).
     */
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
    
    /**
     * Retrieves a team by its name.
     * @param teamName The name of the team to retrieve.
     * @param dto The data transfer object containing details of the team to be updated.
     * @return The updated team's details.
     */
    public TeamReadDTO updateTeam(String teamName, TeamCreateUpdateDTO dto) {
        Team existingTeam = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    
        existingTeam.setTeamName(dto.getTeamName());
    
        if (dto.getDrivers() != null) {
            List<Driver> updatedDrivers = dto.getDrivers().stream()
                    .map(driverDTO -> {
                        Driver driver = new Driver();
                        driver.setName(driverDTO.getName());
                        driver.setSurname(driverDTO.getSurname());
                        driver.setAge(driverDTO.getAge());
                        driver.setTeam(existingTeam);
                        return driver;
                    })
                    .collect(Collectors.toList());
    
            existingTeam.setDrivers(updatedDrivers);
        }
    
        Team updatedTeam = teamRepository.save(existingTeam);
    
        return new TeamReadDTO(
                updatedTeam.getId().toString(),
                updatedTeam.getTeamName(),
                updatedTeam.getDrivers().stream()
                        .map(driver -> driver.getName())
                        .collect(Collectors.toList())
        );
    }    

    /**
     * Deletes a team by its ID.
     *
     * @param id The UUID of the team to delete.
     * @throws EntityNotFoundException if the team is not found.
     */
    public void deleteTeam(UUID id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        teamRepository.delete(team);
    }

    /**
     * Deletes a team by its name.
     *
     * @param teamName The name of the team to delete.
     * @throws EntityNotFoundException if the team is not found.
     */
    public void deleteTeamByName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    
        teamRepository.delete(team);
    }

    /**
     * Retrieves all drivers associated with a team.
     *
     * @param teamId The UUID of the team.
     * @return A list of drivers associated with the team.
     * @throws EntityNotFoundException if the team is not found.
     */
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
