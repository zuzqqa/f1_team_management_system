package edu.pg.formulaapp.services;

import org.springframework.stereotype.Service;

import edu.pg.formulaapp.classes.Driver.Driver;
import edu.pg.formulaapp.repositories.DriverRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * DriverService is responsible for handling driver operations.
 * It provides methods for saving, retrieving, updating, and deleting driver entities.
 */
@Service
public class DriverService {
    /**
     * The repository for driver entities.
     */
    private final DriverRepository driverRepository;

    /**
     * The service for team operations.
     */
    private final TeamService teamService;

    /**
     * Constructs a new DriverService instance.
     * 
     * @param driverRepository the repository for driver entities
     * @param teamService the service for team operations
     */
    public DriverService(DriverRepository driverRepository, TeamService teamService) {
        this.driverRepository = driverRepository;
        this.teamService = teamService;
    }

    /**
     * Saves a new driver entity.
     * 
     * @param driver the driver entity to save
     * @return the saved driver entity
     */
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    /**
     * Retrieves a driver by its ID.
     * 
     * @param id the ID of the driver to retrieve
     * @return the driver entity with the specified ID
     * @throws EntityNotFoundException if the driver with the specified ID does not exist
     */
    public Driver getDriverById(UUID id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
    }

    /**
     * Retrieves all drivers.
     * 
     * @return a list of all driver entities
     */
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    /**
     * Updates an existing driver entity.
     * 
     * @param id the ID of the driver to update
     * @param driverDetails the updated driver details
     * @return the updated driver entity
     */
    public Driver updateDriver(UUID id, Driver driverDetails) {
        Driver existingDriver = getDriverById(id);
        existingDriver.setName(driverDetails.getName());
        existingDriver.setSurname(driverDetails.getSurname());
        existingDriver.setAge(driverDetails.getAge());
        existingDriver.setTeam(driverDetails.getTeam());
        return driverRepository.save(existingDriver);
    }

    /**
     * Deletes a driver entity.
     * 
     * @param id the ID of the driver to delete
     */
    public void deleteDriver(UUID id) {
        Driver driver = getDriverById(id);
        driverRepository.delete(driver);
    }
}
