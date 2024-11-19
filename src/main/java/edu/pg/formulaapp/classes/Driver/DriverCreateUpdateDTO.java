package edu.pg.formulaapp.classes.Driver;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) for creating and updating a driver.
 * Contains information about the driver and the team they are associated with.
 */
public class DriverCreateUpdateDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @Min(value = 1, message = "Age must be a positive integer")
    private int age;

    private String teamIDString;
    private String teamName;

    /**
     * Default constructor.
     */
    public DriverCreateUpdateDTO() {}

    /**
     * Constructor to initialize the DTO with driver and team data.
     *
     * @param name The driver's name.
     * @param surname The driver's surname.
     * @param age The driver's age.
     * @param teamName The name of the team the driver will be assigned to.
     */
    public DriverCreateUpdateDTO(String name, String surname, int age, String teamName) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.teamName = teamName;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getTeamIDString() {
        return teamIDString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamIDString(String teamIDString) {
        this.teamIDString = teamIDString;
    }

    /**
     * Returns the string representation of the object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "DriverCreateUpdateDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", teamName='" + teamName + '\'' +
                ", teamIDString='" + teamIDString + '\'' +
                '}';
    }
}
