package edu.pg.formulaapp.classes.Driver;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) for creating and updating a driver.
 * Contains information about the driver and the team they are associated with.
 */
public class DriverCreateUpdateDTO {
    /**
     * The driver's name.
     */
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    /**
     * The driver's surname.
     */
    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    /**
     * The driver's age.
     */
    @Min(value = 1, message = "Age must be a positive integer")
    private int age;

    /**
     * The ID of the team the driver is assigned to.
     */
    private String teamIDString;

    /**
     * The name of the team the driver is assigned to.
     */
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

    /**
     * Function to get the driver's name.
     * @return The driver's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Function to get the driver's surname.
     * @return The driver's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Function to get the driver's age.
     * @return The driver's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Function to get the name of the team the driver is assigned to.
     * @return The name of the team the driver is assigned to.
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * Function to get the ID of the team the driver is assigned to.
     * @return The ID of the team the driver is assigned to.
     */
    public String getTeamIDString() {
        return teamIDString;
    }

    /**
     * Function to set the driver's name.
     * @param name The driver's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function to set the driver's surname.
     * @param surname The driver's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Function to set the driver's age.
     * @param age The driver's age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Function to set the name of the team the driver is assigned to.
     * @param teamName The name of the team the driver is assigned to.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Function to set the ID of the team the driver is assigned to.
     * @param teamIDString The ID of the team the driver is assigned to.
     */
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
