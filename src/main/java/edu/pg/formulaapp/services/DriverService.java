package edu.pg.formulaapp.services;

import edu.pg.formulaapp.classes.Driver;
import edu.pg.formulaapp.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Driver entity.
 */
@Service
public class DriverService {
    private final DriverRepository driverRepository;

    /**
     * Constructor for DriverService class.
     * 
     * @param driverRepository the driver repository
     */
    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    /**
     * Method to save driver.
     * 
     * @param driver the driver to save
     * @return Driver.
     */
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    /**
     * Method to delete a driver by name and surname.
     * 
     * @param name the driver's name
     * @param surname the driver's surname
     * @return true if the driver was deleted, false otherwise
     */
    public boolean deleteByNameAndSurname(String name, String surname) {
        List<Driver> driversToDelete = driverRepository.findByNameAndSurname(name, surname);
        if (!driversToDelete.isEmpty()) {
            driverRepository.delete(driversToDelete.get(0));
            return true;
        }
        return false;
    }

    /**
     * Method to get all drivers.
     * 
     * @return List of all drivers.
     */
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}
