package edu.pg.formulaapp.repositories;

import edu.pg.formulaapp.classes.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Team entity
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    /**
     * Find team by team name
     */
    List<Team> findByTeamName(String teamName);
}
