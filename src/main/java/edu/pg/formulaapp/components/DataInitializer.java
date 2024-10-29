package edu.pg.formulaapp.components;

import edu.pg.formulaapp.classes.Driver;
import edu.pg.formulaapp.classes.Team;
import edu.pg.formulaapp.services.DriverService;
import edu.pg.formulaapp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The data initializer class of the application.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TeamService teamService;

    @Autowired
    private DriverService driverService;

    /** 
     * The run method of the application.
     * 
     * @param args the command line arguments.
     * @throws Exception the exception.
     * 
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n -> Inicjalizacja danych...");
        Team team = new Team.TeamBuilder().setTeamName("Mercedes").build();
        teamService.save(team);

        Driver driver1 = new Driver.DriverBuilder()
                .setName("Lewis")
                .setSurname("Hamilton")
                .setAge(36)
                .setTeam(team)
                .build();
        driverService.save(driver1);
        
        Driver driver2 = new Driver.DriverBuilder()
                .setName("Valtteri")
                .setSurname("Bottas")
                .setAge(31)
                .setTeam(team)
                .build();
        driverService.save(driver2);

        Team team2 = new Team.TeamBuilder().setTeamName("Red Bull Racing").build();
        teamService.save(team2);

        Driver driver3 = new Driver.DriverBuilder()
                .setName("Max")
                .setSurname("Verstappen")
                .setAge(23)
                .setTeam(team2)
                .build();
        driverService.save(driver3);

        Driver driver4 = new Driver.DriverBuilder()
                .setName("Sergio")
                .setSurname("Perez")
                .setAge(31)
                .setTeam(team2)
                .build();
        driverService.save(driver4);

        System.out.println(" -> Dane zainicjalizowane...\n");
    }
}
