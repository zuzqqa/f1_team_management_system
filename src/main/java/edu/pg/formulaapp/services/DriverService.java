package edu.pg.formulaapp.services;

import org.springframework.stereotype.Service;

import edu.pg.formulaapp.classes.Driver.Driver;
import edu.pg.formulaapp.repositories.DriverRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final TeamService teamService;

    public DriverService(DriverRepository driverRepository, TeamService teamService) {
        this.driverRepository = driverRepository;
        this.teamService = teamService;
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver getDriverById(UUID id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver updateDriver(UUID id, Driver driverDetails) {
        Driver existingDriver = getDriverById(id);
        existingDriver.setName(driverDetails.getName());
        existingDriver.setSurname(driverDetails.getSurname());
        existingDriver.setAge(driverDetails.getAge());
        existingDriver.setTeam(driverDetails.getTeam());
        return driverRepository.save(existingDriver);
    }

    public void deleteDriver(UUID id) {
        Driver driver = getDriverById(id);
        driverRepository.delete(driver);
    }
}
