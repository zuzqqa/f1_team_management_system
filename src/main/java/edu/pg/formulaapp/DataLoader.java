package edu.pg.formulaapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.pg.formulaapp.classes.Team.Team;
import edu.pg.formulaapp.repositories.TeamRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
        if (teamRepository.count() == 0) {
            Team team1 = new Team();
            team1.setTeamName("Team A");

            Team team2 = new Team();
            team2.setTeamName("Team B");

            teamRepository.save(team1);
            teamRepository.save(team2);

            System.out.println("Dodano przykładowe zespoły.");
        }
    }
}
