package com.nrusev.processor;

import com.nrusev.domain.Country;
import com.nrusev.domain.Team;
import com.nrusev.service.CountryService;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 12.10.2016 г..
 */
@Component
public class UKProcessor extends AbstractProcessor {

    private static final String ENGLAND = "England";
    private static final String WALES = "Wales";


    @Autowired
    public UKProcessor(MatchesService matchesService, TeamService teamService, CountryService countryService) {
        super(matchesService, teamService, countryService);
    }

    @Override
    public void process() {
        List<Team> candidates = teamService.findUKTeams();
        List<String> allFromSqlite = matchesService.findAllTeams(ENGLAND);
        List<String> missingTeams = findSQLiteTeams();
        List<Team> removeFoundTeams = removeFoundTeams(allFromSqlite, candidates);

        System.out.println("----missing teams----");
        printTeams(missingTeams);
        System.out.println("---teams not found in db----");
        printTeams(removeFoundTeams);

        Country england = countryService.findByName(ENGLAND).get(0);

        for(String missing : missingTeams){
            Optional<Team> t = teamService.fuzzyMatch(removeFoundTeams, missing);
            if(!t.isPresent()) {
                Team newTeam = new Team();
                newTeam.setCountry(england);
                newTeam.setClub(true);
                newTeam.setTitle(missing);
                newTeam.setKey(missing.toLowerCase());
                newTeam.setNational(false);
                teamService.save(newTeam);
            }
        }
    }


    public List<String> findSQLiteTeams() {
        List<String> allTeams = matchesService.findAllTeams(ENGLAND);
        System.out.println("all english teams " + allTeams.size());
        return allTeams.stream().filter(t-> !teamService.findTeam(t, ENGLAND, WALES).isPresent()).collect(toList());
    }

}
