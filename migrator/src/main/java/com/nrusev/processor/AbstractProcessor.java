package com.nrusev.processor;

import com.nrusev.domain.Team;
import com.nrusev.service.CountryService;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;

import java.util.ArrayList;
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

    abstract List<String> findSQLiteTeams();


    public static void printTeams(List<?> missingTeams){
        missingTeams.forEach(System.out::println);
    }

    public List<Team> removeFoundTeams(List<String> candidates, List<Team> originalTeams){
        List<Team> result = new ArrayList<>();
        for (Team originalTeam : originalTeams) {
            boolean isFound = false;
            for (String candidate : candidates) {
                if (teamService.isTeamSame(candidate, originalTeam)) {
                    isFound = true;
                    break;
                }

            }
            if (!isFound) {
                result.add(originalTeam);
            }
        }
        return  result;
    }
}
