package com.nrusev.exchange.impl;

import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.nrusev.config.XmlSoccerCompetitionsConfig;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.exchange.DataExchanger;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 16.2.2017 г..
 */
@Component
public class XmlSoccerExchanger implements DataExchanger {
    private final XmlSoccerService client;
    private final TeamService teamService;
    private final List<XmlSoccerCompetitionsConfig.Competition> supportedCompetitions;

    @Autowired
    public XmlSoccerExchanger(XmlSoccerService client, TeamService teamService, List<XmlSoccerCompetitionsConfig.Competition> supportedCompetitions) {
        this.client = client;
        this.teamService = teamService;
        this.supportedCompetitions = supportedCompetitions;
    }

    @Override
    public List<Game> findTodayGames() {
//         client.getAllLeagues().forEach(System.out::println);
        return client.getFixturesByDateInterval("2017-02-16","2017-02-28").stream().map(f-> toGame(f)).collect(toList());
    }

    @Override
    public List<Game> findGameByDate(Date from, Date to) {
        return null;
    }

    @Override
    public Set<Team> todaysTeams() {
        return null;
    }

    private Game toGame(GetFixturesResultDto result){
        Game game = new Game();
        supportedCompetitions.stream().filter(c-> result.getLeague().equalsIgnoreCase(c.getName())).findFirst().ifPresent(c->{
            teamService.findTeamByCountryAlpha3Code(result.getHomeTeam(), c.getRegion()).ifPresent(t-> game.setHomeTeam(t));
            teamService.findTeamByCountryAlpha3Code(result.getAwayTeam(), c.getRegion()).ifPresent(t-> game.setVisitorTeam(t));
        });
        game.setPlayAt(result.getDate());
        return game;
    }
}
