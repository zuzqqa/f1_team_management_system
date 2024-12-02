package edu.pg.formulaapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pg.formulaapp.classes.Team.Team;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Team entity
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    /**
     * Find a team by its ID.
     * 
     * @param id the ID of the team
     * @return an Optional containing the team with the specified ID, or an empty Optional if no such team exists
     */
    Optional<Team> findById(UUID id);
    
    /**
     * Find a team by its name.
     * 
     * @param teamName the name of the team
     * @return an Optional containing the team with the specified name, or an empty Optional if no such team exists
     */
    Optional<Team> findByTeamName(String teamName);
}
