package edu.pg.formulaapp.classes.Driver;

/**
 * DTO for reading Driver entity
 */
public class DriverReadDTO {
    /**
     * The `id` variable stores the ID of the driver.
     */
    private String id;

    /**
     * The `name` variable stores the name of the driver.
     */
    private String name;

    /**
     * The `surname` variable stores the surname of the driver.
     */
    private String surname;

    /**
     * The `age` variable stores the age of the driver.
     */
    private int age;

    /**
     * The `teamName` variable stores the name of the team associated with the driver.
     */
    private String teamName;

    /**
     * Constructs a new DriverReadDTO instance.
     */
    public DriverReadDTO() {}

    /**
     * Constructs a new DriverReadDTO instance with the specified attributes.
     * 
     * @param id the driver ID.
     * @param name the name of the driver.
     * @param surname the surname of the driver.
     * @param age the age of the driver.
     * @param teamName the name of the team associated with the driver.
     */
    public DriverReadDTO(String id, String name, String surname, int age, String teamName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.teamName = teamName;
    }

    /**
     * The function `getId()` in Java returns the value of the `id` variable.
     * @return The `id` variable is being returned.
     */
    public String getId() {
        return id;
    }

    /**
     * The function `getName()` in Java returns the value of the `name` variable.
     * @return The `name` variable is being returned.
     */
    public String getName() {
        return name;
    }

    /**
     * The function `getSurname()` in Java returns the value of the `surname` variable.
     * @return The `surname` variable is being returned.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * The function `getAge()` in Java returns the value of the `age` variable.
     * @return The `age` variable is being returned.
     */
    public int getAge() {
        return age;
    }

    /**
     * The function `getTeamName()` in Java returns the value of the `teamName` variable.
     * @return The `teamName` variable is being returned.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * The function `setId()` in Java sets the value of the `id` variable.
     * @param id The `id` variable is being set to the value passed as a parameter.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The function `setName()` in Java sets the value of the `name` variable.
     * @param name The `name` variable is being set to the value passed as a parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The function `setSurname()` in Java sets the value of the `surname` variable.
     * @param surname The `surname` variable is being set to the value passed as a parameter.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * The function `setAge()` in Java sets the value of the `age` variable.
     * @param age The `age` variable is being set to the value passed as a parameter.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * The function `setTeamName()` in Java sets the value of the `teamName` variable.
     * @param teamName The `teamName` variable is being set to the value passed as a parameter.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * The function `toString()` in Java returns the string representation of the object.
     * @return The string representation of the object is being returned.
     */
    @Override
    public String toString() {
        return "DriverReadDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
