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
    Optional<Team> findById(UUID id);
    
    // Pobierz zespół po nazwie
    Optional<Team> findByTeamName(String teamName);
}
