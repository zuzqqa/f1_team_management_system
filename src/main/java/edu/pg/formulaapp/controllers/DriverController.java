package edu.pg.formulaapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.pg.formulaapp.classes.Driver.Driver;
import edu.pg.formulaapp.classes.Driver.DriverCollectionDTO;
import edu.pg.formulaapp.classes.Driver.DriverCreateUpdateDTO;
import edu.pg.formulaapp.classes.Driver.DriverReadDTO;
import edu.pg.formulaapp.classes.Team.Team;
import edu.pg.formulaapp.services.DriverService;
import edu.pg.formulaapp.services.TeamService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;
    private final TeamService teamService;

    public DriverController(DriverService driverService, TeamService teamService) {
        this.driverService = driverService;
        this.teamService = teamService;
    }

    /**
     * Create a new driver and associate them with a team if the team name is provided.
     * 
     * @param dto Data Transfer Object (DTO) containing the driver's details.
     * @return The created driver information, including the team name.
     */
    @PostMapping
    public ResponseEntity<DriverReadDTO> createDriver(@RequestBody DriverCreateUpdateDTO dto) {
        Team team = null;

        // Jeśli ID zespołu jest podane, sprawdzamy czy istnieje
        if (dto.getTeamIDString() != null && !dto.getTeamIDString().isEmpty()) {
            team = teamService.getTeamByID(dto.getTeamIDString());
        } else if (dto.getTeamName() != null && !dto.getTeamName().isEmpty()) {
            // Jeśli zespół jest określony przez nazwę, sprawdzamy lub tworzymy go
            team = teamService.getOrCreateTeamByName(dto.getTeamName());
        }

        Driver driver = new Driver(dto.getName(), dto.getSurname(), dto.getAge(), team);
        Driver savedDriver = driverService.save(driver);

        String teamName = (savedDriver.getTeam() != null) ? savedDriver.getTeam().getTeamName() : "Brak zespołu";
        DriverReadDTO response = new DriverReadDTO(
                savedDriver.getId().toString(),
                savedDriver.getName(),
                savedDriver.getSurname(),
                savedDriver.getAge(),
                teamName
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * Retrieve a driver's details by their ID.
     * 
     * @param id The UUID of the driver.
     * @return The driver's details including their team name.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DriverReadDTO> getDriverById(@PathVariable UUID id) {
        Driver driver = driverService.getDriverById(id);
        String teamName = (driver.getTeam() != null) ? driver.getTeam().getTeamName() : "No Team";
        DriverReadDTO response = new DriverReadDTO(driver.getId().toString(), driver.getName(), driver.getSurname(), driver.getAge(), teamName);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve all drivers from the database.
     * 
     * @return A list of all drivers with basic information (name and surname).
     */
    @GetMapping
    public ResponseEntity<List<DriverCollectionDTO>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        List<DriverCollectionDTO> response = drivers.stream()
                .map(driver -> new DriverCollectionDTO(driver.getId().toString(), driver.getName(), driver.getSurname()))
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Update an existing driver's details.
     * 
     * @param id The UUID of the driver to be updated.
     * @param dto The data transfer object containing the updated details.
     * @return The updated driver information, including the team name.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DriverReadDTO> updateDriver(@PathVariable UUID id, @RequestBody DriverCreateUpdateDTO dto) {
        Team team = null; // Default: no team

        // Check if a team name is provided and associate the team if found
        if (dto.getTeamName() != null && !dto.getTeamName().isEmpty()) {
            team = teamService.getOrCreateTeamByName(dto.getTeamName());
        }

        // Create the updated driver
        Driver updatedDriver = new Driver(dto.getName(), dto.getSurname(), dto.getAge(), team);

        // Update the driver in the database
        Driver driver = driverService.updateDriver(id, updatedDriver);
        String teamName = (driver.getTeam() != null) ? driver.getTeam().getTeamName() : "No Team";
        DriverReadDTO response = new DriverReadDTO(driver.getId().toString(), driver.getName(), driver.getSurname(), driver.getAge(), teamName);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a driver by their ID.
     * 
     * @param id The UUID of the driver to be deleted.
     * @return HTTP response indicating the deletion status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable UUID id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}
