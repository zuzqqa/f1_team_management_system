package edu.pg.formulaapp.components;

import edu.pg.formulaapp.classes.Driver;
import edu.pg.formulaapp.classes.Team;
import edu.pg.formulaapp.services.DriverService;
import edu.pg.formulaapp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * The custom command line runner.
 */
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    @Autowired
    private DriverService driverService;

    @Autowired
    private TeamService teamService;

    /**
     * The run method of the command line runner.
     *
     * @param args the command line arguments.
     * @throws Exception if an error occurs.
     */
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            listCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    listCommands();
                    break;
                case "2":
                    listDrivers();
                    break;
                case "3":
                    addDriver(scanner);
                    break;
                case "4":
                    deleteDriver(scanner);
                    break;
                case "5":
                    listTeams(); 
                    break;
                case "6":
                    addTeam(scanner); 
                    break;
                case "7":
                    deleteTeam(scanner); 
                    break;
                case "8":
                    printDriversOfTeam(scanner); 
                    break;
                case "9":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    /**
     * Lists available commands.
     */
    private void listCommands() {
        System.out.println("Available commands:");
        System.out.println("[1] List commands - displays available commands");
        System.out.println("[2] List drivers - displays all drivers");
        System.out.println("[3] Add driver - adds a new driver");
        System.out.println("[4] Delete driver - removes an existing driver");
        System.out.println("[5] List teams - displays all teams"); 
        System.out.println("[6] Add team - adds a new team"); 
        System.out.println("[7] Delete team - removes an existing team"); 
        System.out.println("[8] List drivers of a team - displays drivers for a specific team");
        System.out.println("[9] Exit - ends the application");
    }

    /**
     * Lists all drivers using the DriverService.
     */
    private void listDrivers() {
        System.out.println(" -> Listing all drivers...");
        List<Driver> drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()) {
            System.out.println("    No drivers in the database.");
        } else {
            System.out.println("    List of drivers:");
            for (Driver driver : drivers) {
                System.out.println(driver);
            }
        }
        System.out.println("\n");
    }

    /**
     * Adds a new driver based on user input.
     *
     * @param scanner the scanner to read user input.
     */
    private void addDriver(Scanner scanner) {
        while (true) {
            System.out.println(" -> Starting the process of adding a new driver (press 0 anytime to cancel)...");
            System.out.print("  Enter driver's first name (or press 0 to cancel): ");
            String name = scanner.nextLine();
            
            if (name.equals("0")) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return; 
            }

            System.out.print("  Enter driver's last name (or press 0 to cancel): ");
            String surname = scanner.nextLine();
            
            if (surname.equals("0")) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return;
            }

            System.out.print("  Enter driver's age (or press 0 to cancel): ");
            int age;
            while (true) {
                try {
                    age = Integer.parseInt(scanner.nextLine());
                    if (age <= 0) {
                        System.out.println("!!! Age must be a positive number. Please try again. !!!");
                        continue;
                    }
                    break; 
                } catch (NumberFormatException e) {
                    System.out.println("!!! Please enter a valid number. !!!");
                }
            }

            if (age == 0) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return;
            }

            System.out.print("  Enter team name (if unknown, press Enter): ");
            String teamName = scanner.nextLine();

            if (teamName.equals("0")) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return;
            }

            Driver newDriver;
            if (!teamName.isEmpty()) {
                newDriver = new Driver.DriverBuilder()
                        .setName(name)
                        .setSurname(surname)
                        .setAge(age)
                        .setTeam(teamService.getTeamByName(teamName).stream().findFirst().orElse(null)) 
                        .build();
            } else {
                newDriver = new Driver.DriverBuilder()
                        .setName(name)
                        .setSurname(surname)
                        .setAge(age)
                        .build();
            }

            driverService.save(newDriver);
            System.out.println("Driver has been added: " + newDriver);
            return; 
        }
    }


    /**
     * Deletes an existing driver based on user input.
     * 
     * @param scanner the scanner to read user input.
     */
    private void deleteDriver(Scanner scanner) {
        while (true) {
            System.out.println(" -> Starting the process of deleting the driver (press 0 anytime to cancel)...");
            System.out.print("  Enter first name of the driver to delete (or press 0 to cancel): ");
            String name = scanner.nextLine();

            if (name.equals("0")) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return;
            }
            
            System.out.print("  Enter last name of the driver to delete (or press 0 to cancel): ");
            String surname = scanner.nextLine();
            
            if (surname.equals("0")) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return; 
            }
    
            boolean isDeleted = driverService.deleteByNameAndSurname(name, surname);
            if (isDeleted) {
                System.out.println("    Driver with first name " + name + " and last name " + surname + " has been deleted.");
                return; 
            } else {
                System.out.println("    No driver found with first name " + name + " and last name " + surname + ".");
                System.out.println(" -> Please try again or press 0 to cancel.");
            }
        }
    }

    /**
     * Lists all teams using the TeamService.
     */
    private void listTeams() {
        System.out.println(" -> Listing all teams...");
        List<Team> teams = teamService.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("    No teams in the database.");
        } else {
            System.out.println("    List of teams:");
            for (Team team : teams) {
                System.out.println(team);
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Adds a new team based on user input.
     *
     * @param scanner the scanner to read user input.
     */
    private void addTeam(Scanner scanner) {
        while (true) {
            System.out.println(" -> Starting the process of adding a new team (press 0 anytime to cancel)...");
            System.out.print("  Enter team's name (or press 0 to cancel): ");
            String teamName = scanner.nextLine();

            if (teamName.equals("0")) {
                System.out.println(" -> Cancellation of the add operation.\n");
                return;
            }

            Team newTeam = new Team.TeamBuilder()
                    .setTeamName(teamName)
                    .build();

            teamService.addTeam(newTeam);
            System.out.println("    Team has been added: " + newTeam);
            return; 
        }
    }

    /**
     * Deletes an existing team based on user input.
     *
     * @param scanner the scanner to read user input.
     */
    private void deleteTeam(Scanner scanner) {
        while (true) {
            System.out.println(" -> Starting the process of deleting a team (press 0 anytime to cancel)...");
            System.out.print("  Enter team's name to delete (or press 0 to cancel): ");
            String teamName = scanner.nextLine();

            if (teamName.equals("0")) {
                System.out.println(" -> Cancellation of the delete operation.\n");
                return;
            }

            teamService.deleteByName(teamName);
            return; 
        }
    }

    /**
     * Prints drivers of a specific team based on user input.
     *
     * @param scanner the scanner to read user input.
     */
    private void printDriversOfTeam(Scanner scanner) {
        while (true) {
            System.out.println(" -> Starting the process of listing drivers of a team (press 0 anytime to cancel)...");
            System.out.print("  Enter team's name to list drivers (or press 0 to cancel): ");
            String teamName = scanner.nextLine();

            if (teamName.equals("0")) {
                System.out.println(" -> Cancellation of the operation.\n");
                return;
            }

            teamService.printDriversOfTeam(teamName);
            return; 
        }
    }
}
