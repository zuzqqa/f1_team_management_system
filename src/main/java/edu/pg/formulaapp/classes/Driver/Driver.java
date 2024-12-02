package edu.pg.formulaapp.classes.Driver;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import edu.pg.formulaapp.classes.Team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * A class representing a driver in a Formula 1 racing team.
 */
@Entity
@Table(name = "drivers")
public class Driver implements Comparable<Driver>, Serializable {
    /**
     * Id of the driver.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * First name of the driver.
     */
    private String name;

    /**
     * Surname of the driver.
     */
    private String surname;

    /**
     * Age of the driver.
     */
    private int age;

    /**
     * Team to which the driver belongs.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * Constructs a new Driver instance.
     */
    public Driver() {
    }
    
    /**
     * Constructs a new Driver instance with the specified attributes.
     *
     * @param name    the first name of the driver. This value cannot be null or empty.
     * @param surname the surname of the driver. This value cannot be null or empty.
     * @param age     the age of the driver. This value must be a positive integer representing the driver's age in years.
     * @param team    the team to which the driver belongs. This value may be null if the driver is not currently associated with any team.
     */
    public Driver(String name, String surname, int age, Team team) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || age <= 0) {
            throw new IllegalArgumentException("Invalid driver details");
        }
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.team = team;
    }

    /**
     * Returns the unique identifier for the driver.
     * @return the unique identifier for the driver.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the first name of the driver.
     * @return the first name of the driver.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the driver.
     * @return the surname of the driver.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the age of the driver.
     * @return the age of the driver.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the team to which the driver belongs.
     * @return the team to which the driver belongs.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team to which the driver belongs.
     * @param team the team to which the driver belongs.
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Sets the surname of the driver.
     * @param surname the surname of the driver.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets the first name of the driver.
     * @param name the first name of the driver.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the age of the driver.
     * @param age the age of the driver.
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Compares this Driver object with the specified Driver object for order.
     * @param o the Driver object to be compared.
     * @return a negative integer, zero, or a positive integer as this Driver object is less than, equal to, or greater than the specified Driver object.
     */
    @Override
    public int compareTo(Driver o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(surname, driver.surname);
    }

    /**
     * Returns a string representation of the Driver object.
     *
     * @return a string representation of the Driver object.
     */
    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", team=" + (team != null ? team.getTeamName() : "No team") +
                '}';
    }

    /**
     * A builder class for the Driver object.
     */
    public static class DriverBuilder {
        /**
         * First name of the driver.
         */
        private String name;

        /**
         * Surname of the driver.
         */
        private String surname;

        /**
         * Age of the driver.
         */
        private int age;

        /**
         * Team to which the driver belongs.
         */
        private Team team;

        /**
         * Constructs a new DriverBuilder instance.
         */
        public DriverBuilder() {}
        
        /**
         * Sets the first name of the driver.
         * @param name the first name of the driver.
         * @return the DriverBuilder object.
         */
        public DriverBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the surname of the driver.
         * @param surname the surname of the driver.
         * @return the DriverBuilder object.
         */
        public DriverBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Sets the age of the driver.
         * @param age the age of the driver.
         * @return the DriverBuilder object.
         */
        public DriverBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        /**
         * Sets the team to which the driver belongs.
         * @param team the team to which the driver belongs.
         * @return the DriverBuilder object.
         */
        public DriverBuilder setTeam(Team team) {
            this.team = team;
            return this;
        }

        /**
         * Builds a new Driver object with the specified attributes.
         * @return a new Driver object.
         */
        public Driver build() {
            return new Driver(name, surname, age, team);
        }
    }
}
