package edu.pg.formulaapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.pg.formulaapp.classes.Driver;
import edu.pg.formulaapp.classes.Driver.DriverBuilder;
import edu.pg.formulaapp.classes.DriverDTO;
import edu.pg.formulaapp.classes.Team;

public class App {
    public static void main(String[] args) {
        System.out.println(" -> Adding teams...");
        Team teamMercedes = new Team("Mercedes", new ArrayList<>());
        Team teamFerrari = new Team("Ferrari", new ArrayList<>());

        System.out.println(teamMercedes);
        System.out.println(teamFerrari + "\n");

        System.out.println(" -> Adding drivers...\n");
        Driver driver1 = new DriverBuilder()
                .setName("Lewis")
                .setSurname("Hamilton")
                .setAge(39)
                .setTeam(teamMercedes)
                .build();

        Driver driver2 = new DriverBuilder()
                .setName("Charles")
                .setSurname("Leclerc")
                .setAge(25)
                .setTeam(teamFerrari)
                .build();
        
        Driver driver3 = new DriverBuilder()
                .setName("George")
                .setSurname("Russell")
                .setAge(23)
                .setTeam(teamFerrari)
                .build();

        Driver driver4 = new DriverBuilder()
                .setName("Carlos")
                .setSurname("Sainz")
                .setAge(26)
                .setTeam(teamFerrari)
                .build();

        System.out.println(" -> Adding drivers to teams...\n");
        teamMercedes.addDriver(driver1);
        teamFerrari.addDriver(driver2);
        teamMercedes.addDriver(driver3);
        teamFerrari.addDriver(driver4);

        System.out.println(" -> Displaying drivers...");
        System.out.println(driver1);
        System.out.println(driver2 + "\n");

        System.out.println(" -> Creating DTO objects...\n");
        DriverDTO driverDto1 = new DriverDTO(driver1.getName(), driver1.getSurname(), driver1.getAge(), teamMercedes.getTeamName());
        DriverDTO driverDto2 = new DriverDTO(driver2.getName(), driver2.getSurname(), driver2.getAge(), teamFerrari.getTeamName());

        System.out.println(" -> Displaying DTO objects...\n");
        System.out.println(driverDto1);
        System.out.println(driverDto2);

        System.out.println(" -> Adding all drivers to a Set using Stream API...\n");
        Set<Driver> allDriversStream = Stream.concat(
                teamMercedes.getDrivers().stream(),
                teamFerrari.getDrivers().stream()
            ).collect(Collectors.toSet());

        System.out.println(" -> All drivers from Stream API:");
        allDriversStream.forEach(System.out::println);
        System.out.println();

        System.out.println(" -> Filtering drivers under 30 years old and sorting by surname...\n");
        List<Driver> filteredSortedDrivers = allDriversStream.stream()
                .filter(driver -> driver.getAge() < 30) 
                .sorted((d1, d2) -> d1.getSurname().compareTo(d2.getSurname()))
                .collect(Collectors.toList());

        System.out.println(" -> Filtered and sorted drivers:");
        filteredSortedDrivers.forEach(System.out::println);

        System.out.println(" -> Transforming drivers to DTO and sorting...\n");
        List<DriverDTO> driverDTOs = Stream.concat(
                teamMercedes.getDrivers().stream(),
                teamFerrari.getDrivers().stream()
            )
            .map(driver -> new DriverDTO(driver.getName(), driver.getSurname(), driver.getAge(), driver.getTeam().getTeamName())) // Transformacja do DTO
            .sorted()
            .collect(Collectors.toList()); 

        System.out.println(" -> Driver DTOs after transformation and sorting:");
        driverDTOs.forEach(System.out::println);
        System.out.println();

        System.out.println(" -> Serializing drivers...");
        serializeDrivers(driverDTOs);

        List<DriverDTO> deserializedDrivers = deserializeDrivers();
        System.out.println(" -> Deserialized Drivers:");
        deserializedDrivers.forEach(System.out::println);
        System.out.println();

        System.out.println(" -> Parallel processing of deserialized drivers...\n");
        int customPoolSize = 2; 
        try (ForkJoinPool forkJoinPool = new ForkJoinPool(customPoolSize)) {
            forkJoinPool.submit(() -> {
                deserializedDrivers.parallelStream().forEach(driver -> {
                    try {
                        System.out.println("Driver: " + driver);
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); 
                    }
                });
            });

            forkJoinPool.shutdown();
            try {
                if (!forkJoinPool.awaitTermination(1, TimeUnit.MINUTES)) {
                    forkJoinPool.shutdownNow(); 
                }
            } catch (InterruptedException e) {
                forkJoinPool.shutdownNow(); 
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All tasks completed.");
    }

    private static void serializeDrivers(List<DriverDTO> drivers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("drivers.bin"))) {
            oos.writeObject(drivers);
            System.out.println("Drivers have been serialized. \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<DriverDTO> deserializeDrivers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("drivers.bin"))) {
            return (List<DriverDTO>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
