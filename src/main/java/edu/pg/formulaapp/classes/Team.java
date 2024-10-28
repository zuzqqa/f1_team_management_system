package edu.pg.formulaapp.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a Formula 1 racing team.
 */
public class Team implements Comparable<Team>, Serializable {
    private String teamName;
    private List<Driver> drivers;

    /** 
     * Constructs a new Team instance with the specified attributes.
     * 
     * @param teamName the name of the team. This value cannot be null or empty.
     * @param drivers  the list of drivers associated with the team. This value cannot be null.
     */
    public Team(String teamName, List<Driver> drivers) {
        this.teamName = teamName;
        this.drivers = drivers;
    }

    /**
     * Returns the name of the team.
     * @return the name of the team.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns the list of drivers associated with the team.
     * @return the list of drivers associated with the team.
     */
    public List<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Sets the name of the team.
     * @param teamName the name of the team.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Sets the list of drivers associated with the team.
     * @param drivers the list of drivers associated with the team.
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    /** 
     * Compares this Team object with the specified Team object for order.
     * 
     * @param o the Team object to be compared.
     * @return a negative integer, zero, or a positive integer as this Team object is less than, equal to, or greater than the specified Team object.
     */
    @Override
    public int compareTo(Team o) {
        return this.teamName.compareTo(o.teamName);
    }
    
    /** 
     * Returns a hash code value for the object.
     * 
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(teamName, drivers);
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
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName) &&
                Objects.equals(drivers, team.drivers);
    }

    /** 
     * Returns a string representation of the object.
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder driversList = new StringBuilder();
        if (drivers != null && !drivers.isEmpty()) {
            for (Driver driver : drivers) {
                driversList.append(driver != null ? driver.toString() : "No driver available").append(", ");
            }
            // Usuwamy ostatni przecinek i spację, jeśli są kierowcy
            if (driversList.length() > 0) {
                driversList.setLength(driversList.length() - 2);
            }
        } else {
            driversList.append("No drivers available");
        }
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", drivers=[" + driversList + "]" +
                '}';
    }


    /**
     * Adds a driver to the team.
     * @param driver the driver to be added to the team.
     */
    public void addDriver(Driver driver) {
        drivers.add(driver); 
    }
    
    /**
     * A builder class for the Team object.
     */
    public static class TeamBuilder {
        private String teamName;
        private List<Driver> drivers;

        /**
         * Sets the name of the team.
         * @param teamName the name of the team.
         * @return the TeamBuilder instance.
         */
        public TeamBuilder setTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        /**
         * Sets the list of drivers associated with the team.
         * @param drivers the list of drivers associated with the team.
         * @return the TeamBuilder instance.
         */
        public TeamBuilder setDrivers(List<Driver> drivers) {
            this.drivers = drivers;
            return this;
        }

        /**
         * Builds a new Team instance with the specified attributes.
         * @return a new Team instance.
         */
        public Team build() {
            return new Team(teamName, drivers);
        }
    }
}
