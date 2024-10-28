package edu.pg.formulaapp.classes;

import java.io.Serializable;

/**
 * A class representing a driver in a Formula 1 racing team.
 */
public class DriverDTO implements Comparable<DriverDTO>, Serializable {
    private String name;
    private String surname;
    private int age;
    private String teamName;

    /**
     * Constructor for DriverDTO
     * 
     * @param name driver's name
     * @param surname driver's surname
     * @param age driver's age
     * 
     */
    public DriverDTO(String name, String surname, int age, String teamName) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.teamName = teamName;
    }
    
    /**
     * Gets the name of the driver.
     *
     * @return the name of the driver.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the surname of the driver.
     *
     * @return the surname of the driver.
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * Gets the age of the driver.
     *
     * @return the age of the driver.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the name of the team the driver belongs to.
     *
     * @return the name of the team the driver belongs to.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets the name of the driver.
     * @param name the name of the driver.
     */
    public void setDriverName(String name) {
        this.name = name;
    }

    /**
     * Sets the surname of the driver.
     * @param surname the surname of the driver.
     */
    public void setDriverSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets the age of the driver.
     * @param age the age of the driver.
     */
    public void setDriverAge(int age) {
        this.age = age;
    }

    /**
     * Sets the name of the team the driver belongs to.
     *
     * @param teamName the name of the team the driver belongs to.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Compares this driver to another driver based on their names.
     * 
     * @param other the driver to compare to.
     * @return a negative integer, zero, or a positive integer as this driver is less than, equal to, or greater than the specified driver.
     */
    @Override
    public int compareTo(DriverDTO other) {
        return this.name.compareTo(other.name);
    }

    /** 
     * Returns a string representation of the object.
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "DriverDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    /**
     * A builder class for the DriverDTO object.
     */
    public static class DriverDTOBuilder {
        private String name;
        private String surname;
        private int age;
        private String teamName;

        /**
         * Sets the name of the driver.
         * @param name the name of the driver.
         * @return the DriverDTOBuilder instance.
         */
        public DriverDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the surname of the driver.
         * @param surname the surname of the driver.
         * @return the DriverDTOBuilder instance.
         */
        public DriverDTOBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Sets the age of the driver.
         * @param age the age of the driver.
         * @return the DriverDTOBuilder instance.
         */
        public DriverDTOBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        /**
         * Sets the name of the team the driver belongs to.
         * @param teamName the name of the team the driver belongs to.
         * @return the DriverDTOBuilder instance.
         */
        public DriverDTOBuilder setTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        /**
         * Builds a new DriverDTO instance with the specified attributes.
         * @return a new DriverDTO instance.
         */
        public DriverDTO build() {
            return new DriverDTO(name, surname, age, teamName);
        }
    }
}
