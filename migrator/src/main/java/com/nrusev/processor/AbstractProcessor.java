package com.nrusev.processor;

import com.nrusev.service.CountryService;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Nikolay Rusev on 12.10.2016 г..
 */
public abstract class AbstractProcessor implements Processor {
    final MatchesService matchesService;

    final TeamService teamService;

    final CountryService countryService;

    public AbstractProcessor(MatchesService matchesService, TeamService teamService, CountryService countryService){
        this.matchesService = matchesService;
        this.teamService = teamService;
        this.countryService = countryService;
    }

    public static void printTeams(List<String> missingTeams){
        System.out.println("---Teams---");
        missingTeams.forEach(System.out::println);
    }


}
