package edu.pg.formulaapp.classes;

import java.io.Serializable;

/**
 * A class representing a driver in a Formula 1 racing team.
 */
public class Driver implements Comparable<Driver>, Serializable {
    private String name;
    private String surname;
    private int age;
    private Team team;
    
    /**
     * Constructs a new Driver instance with the specified attributes.
     *
     * @param name    the first name of the driver. This value cannot be null or empty.
     * @param surname the surname of the driver. This value cannot be null or empty.
     * @param age     the age of the driver. This value must be a positive integer representing the driver's age in years.
     * @param team    the team to which the driver belongs. This value may be null if the driver is not currently associated with any team.
     */
    public Driver(String name, String surname, int age, Team team) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.team = team;
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
        return name.hashCode() + surname.hashCode();
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
        return name.equals(driver.name) && surname.equals(driver.surname);
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
                ", team=" + team.getTeamName() +
                '}';
    }

    /**
     * A builder class for the Driver object.
     */
    public static class DriverBuilder {
        private String name;
        private String surname;
        private int age;
        private Team team;

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
