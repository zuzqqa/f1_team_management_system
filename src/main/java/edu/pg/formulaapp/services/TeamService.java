package edu.pg.formulaapp.services;

import edu.pg.formulaapp.classes.Driver;
import edu.pg.formulaapp.classes.Team;
import edu.pg.formulaapp.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service class for Team entity.
 */
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    /**
     * Constructor for TeamService class.
     * @param teamRepository
     */
    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Method to get all teams.
     * @return List of all teams.
     */
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    /**
     * Method to find team by name.
     * @param teamName the name of the team.
     * @return Optional of Team.
     */
    public Optional<Team> findByName(String teamName) {
        return teamRepository.findByTeamName(teamName).stream().findFirst();
    }

    /**
     * Method to save team.
     * @param team
     * @return Team.
     */
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    /**
     * Method to delete a team by name.
     * @param teamName the name of the team.
     * @return true if the team was deleted, false otherwise.
     */
    public boolean deleteByName(String teamName) {
        Optional<Team> teamOptional = findByName(teamName);
        if (teamOptional.isPresent()) {
            teamRepository.delete(teamOptional.get());
            System.out.println("    " + teamName + " deleted.");
            return true;
        } 

        System.out.println("    " + teamName + " does not exist.");
        return false;
    }

    /**
     * Method to add a new team.
     * @param team the team to add.
     * @return the added team.
     */
    public Team addTeam(Team team) {
        return save(team);
    }

    /**
     * Method to get team by name.
     * @param teamName the name of the team.
     * @return List of teams.
     */
    public List<Team> getTeamByName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }

    /**
     * Method to print drivers of a specific team.
     * @param teamName the name of the team.
     */
    public void printDriversOfTeam(String teamName) {
        Optional<Team> teamOptional = findByName(teamName);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            List<Driver> drivers = team.getDrivers();
            if (drivers.isEmpty()) {
                System.out.println("No drivers in team '" + team.getTeamName() + "'.");
            } else {
                System.out.println("Drivers in team '" + team.getTeamName() + "':");
                for (Driver driver : drivers) {
                    System.out.println(driver);
                }
            }
        } else {
            System.out.println("Team '" + teamName + "' not found.");
        }
    }
}
