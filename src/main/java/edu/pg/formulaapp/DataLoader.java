package edu.pg.formulaapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.pg.formulaapp.classes.Team.Team;
import edu.pg.formulaapp.classes.Driver.Driver;
import edu.pg.formulaapp.repositories.TeamRepository;
import edu.pg.formulaapp.repositories.DriverRepository;

import java.util.List;

/**
 * DataLoader is responsible for loading initial data into the application.
 */
@Component
public class DataLoader implements CommandLineRunner {
    /**
     * The TeamRepository object.
     */
    @Autowired
    private TeamRepository teamRepository;

    /**
     * The DriverRepository object.
     */
    @Autowired
    private DriverRepository driverRepository;

    /** 
     * Default constructor for DataLoader class.
     */
    public DataLoader() {}

    /** 
     * The run method of the DataLoader class.
     * 
     * @param args the command line arguments.
     * @throws Exception the exception that may be thrown.
     */
    @Override
    public void run(String... args) throws Exception {
        if (teamRepository.count() == 0) {
            // Create teams
            Team redBull = new Team();
            redBull.setTeamName("Red Bull Racing");

            Team mercedes = new Team();
            mercedes.setTeamName("Mercedes-AMG Petronas");

            Team ferrari = new Team();
            ferrari.setTeamName("Scuderia Ferrari");

            Team mclaren = new Team();
            mclaren.setTeamName("McLaren");

            // Save teams to the repository
            redBull = teamRepository.save(redBull);
            mercedes = teamRepository.save(mercedes);
            ferrari = teamRepository.save(ferrari);
            mclaren = teamRepository.save(mclaren);

            // Create drivers
            Driver verstappen = new Driver("Max", "Verstappen", 26, redBull);
            Driver perez = new Driver("Sergio", "Pérez", 33, redBull);

            Driver hamilton = new Driver("Lewis", "Hamilton", 39, mercedes);
            Driver russell = new Driver("George", "Russell", 25, mercedes);

            Driver leclerc = new Driver("Charles", "Leclerc", 26, ferrari);
            Driver sainz = new Driver("Carlos", "Sainz", 29, ferrari);

            Driver norris = new Driver("Lando", "Norris", 24, mclaren);
            Driver piastri = new Driver("Oscar", "Piastri", 23, mclaren);

            // Save drivers to the repository
            driverRepository.saveAll(List.of(verstappen, perez, hamilton, russell, leclerc, sainz, norris, piastri));

            System.out.println("Dodano zespoły i kierowców Formuły 1.");
        }
    }
}
