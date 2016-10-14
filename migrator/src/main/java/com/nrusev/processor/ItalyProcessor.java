package com.nrusev.processor;

import com.nrusev.domain.Country;
import com.nrusev.domain.Team;
import com.nrusev.service.CountryService;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 12.10.2016 г..
 */
@Component
public class ItalyProcessor  extends AbstractProcessor{

    private static final String COUNTRY = "Italy";

    @Autowired
    public ItalyProcessor(MatchesService matchesService, TeamService teamService, CountryService countryService) {
        super(matchesService, teamService, countryService);
    }

    @Override
    public void process() {
        List<String> missingTeams = findSQLiteTeams();
        printTeams(missingTeams);

        Country italy = this.countryService.findByName(COUNTRY).get(0);
        missingTeams.forEach(t->{
            Team newTeam = new Team();
            newTeam.setCountry(italy);
            newTeam.setClub(true);
            newTeam.setTitle(t);
            newTeam.setKey(t.toLowerCase());
            newTeam.setNational(false);
            teamService.save(newTeam);
        });


    }

    public List<String> findSQLiteTeams(){
        List<String> allTeams = matchesService.findAllTeams(COUNTRY);
        return allTeams.stream().filter(t-> !teamService.findTeam(t,COUNTRY).isPresent()).collect(toList());
    }

}
