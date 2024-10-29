package edu.pg.formulaapp.repositories;

import edu.pg.formulaapp.classes.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Driver entity
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {
    /**
     * Method to find driver by name and surname.
     *
     * @param name    the driver name
     * @param surname the driver surname
     * @return List of drivers.
     */
    List<Driver> findByNameAndSurname(String name, String surname);
}
