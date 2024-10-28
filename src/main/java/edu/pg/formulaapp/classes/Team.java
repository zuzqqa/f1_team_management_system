package edu.pg.formulaapp.classes;

import java.util.List;
import java.util.Objects;

public class Team implements Comparable<Team> {
    private String teamName;
    private List<Driver> drivers;

    public Team(String teamName, List<Driver> drivers) {
        this.teamName = teamName;
        this.drivers = drivers;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", drivers=" + drivers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName) &&
                Objects.equals(drivers, team.drivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, drivers);
    }

    @Override
    public int compareTo(Team o) {
        return this.teamName.compareTo(o.teamName);
    }
}
