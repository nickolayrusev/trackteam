package com.nrusev.processor;

import com.nrusev.domain.Team;
import com.nrusev.service.CountryService;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 14.10.2016 г..
 */
@Component
public class SpainProcessor  extends AbstractProcessor{

    private static final String SPAIN = "Spain";

    @Autowired
    public SpainProcessor(MatchesService matchesService, TeamService teamService, CountryService countryService) {
        super(matchesService, teamService, countryService);
    }

    @Override
    public void process() {
        List<String> allFromSQLite = matchesService.findAllTeams(SPAIN);
        List<String> missingTeams = findSQLiteTeams();
        List<Team> originalTeams = teamService.findByCountries(SPAIN);
        List<Team> removeFoundTeams = removeFoundTeams(allFromSQLite, originalTeams);

        System.out.println("---all teams --");
        printTeams(missingTeams);
        System.out.println("--- not found teams ---");
        printTeams(removeFoundTeams);

        for(String missing : missingTeams){
            System.out.println( missing + " will be inserted"  );
        }
    }

    @Override
    List<String> findSQLiteTeams() {
        return matchesService.findAllTeams(SPAIN).stream().filter(t-> !teamService.findTeam(t, SPAIN).isPresent()).collect(toList());
    }
}
