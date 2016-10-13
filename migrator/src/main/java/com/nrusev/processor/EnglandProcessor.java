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
 * Created by Nikolay Rusev on 12.10.2016 г..
 */
@Component
public class EnglandProcessor extends AbstractProcessor {

    private static final String COUNTRY = "England";

    @Autowired
    public EnglandProcessor(MatchesService matchesService, TeamService teamService, CountryService countryService) {
        super(matchesService, teamService, countryService);
    }

    @Override
    public void process() {
        List<Team> candidates = teamService.findByCountryName(COUNTRY);
        List<String> missingTeams = findMissingTeams();

        printTeams(missingTeams);
        for(String missing : missingTeams){
            Optional<Team> team = teamService.fuzzyMatch(candidates, missing);
            System.out.println(" for team " + missing + " possible match is "+ team );
        }
    }


    public List<String> findMissingTeams(){
        List<String> allTeams = matchesService.findAllTeams(COUNTRY);
        System.out.println("all english teams " + allTeams.size());
        return allTeams.stream().filter(t-> !teamService.findTeam(t,COUNTRY).isPresent()).collect(toList());
    }
}
