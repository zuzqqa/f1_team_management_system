package edu.pg.formulaapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pg.formulaapp.classes.Driver.Driver;
import edu.pg.formulaapp.classes.Team.Team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Driver entity
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {
    /**
     * Finds all drivers
     * @return list of all drivers
     */
    List<Driver> findAll();

    /**
     * Finds driver by id
     * @param id id of the driver
     * @return driver with specified id
     */
    Optional<Driver> findById(UUID id);

    /**
     * Finds driver by team
     * @param team team of the driver
     * @return list of drivers in the team
     */
    List<Driver> findByTeam(Team team);
}
