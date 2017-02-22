package com.nrusev.exchange.impl;

import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.model.enums.Leagues;
import com.nrusev.domain.Game;
import com.nrusev.domain.Round;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.exchange.DataExchanger;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 16.2.2017 г..
 */
@Component
public class XmlSoccerExchanger implements DataExchanger {
    private final XmlSoccerService client;

    @Autowired
    public XmlSoccerExchanger(XmlSoccerService client, TeamService teamService, List<Competition> supportedCompetitions) {
        this.client = client;
    }

    @Override
    public List<GameDto> findTodayGames() {
         client.getAllLeagues().forEach(System.out::println);
//        client.getFixturesByLeagueAndSeason("Scottish Premier League","1617").forEach(System.out::println);
        return client.getFixturesByDateInterval("2017-02-16","2017-02-28").stream().map(r->toGame(r, SeasonKeys.SEASON_2016_2017)).collect(toList());
    }

    @Override
    public List<GameDto> findGameByDate(Date from, Date to) {
        return null;
    }

    @Override
    public List<GameDto> getFixturesByLeagueAndSeason(String league, SeasonKeys season) {
        //xmlsoccer wants season 2016/2017 as 1617. Next line do that thing
        String seasonString = Arrays.stream(season.getKey().split("/")).reduce("", (s, t) -> s + t.substring(2,4));
        return client.getFixturesByLeagueAndSeason(league,seasonString).stream().map(g->toGame(g,season)).collect(toList());
    }


    private GameDto toGame(GetFixturesResultDto result, SeasonKeys season){
        GameDto gameDto = new GameDto();
        gameDto.setHomeTeam(result.getHomeTeam());
        gameDto.setVisitorTeam(result.getAwayTeam());
        gameDto.setSeasonKey(season);
        gameDto.setPlayAt(result.getDate());
        Optional.ofNullable(result.getHomeGoals()).ifPresent(g->{
            gameDto.setHomeTeamGoals(g.longValue());
        });
        Optional.ofNullable(result.getAwayGoals()).ifPresent(g->{
            gameDto.setVisitorTeamGoals(g.longValue());
        });
        gameDto.setLeague(result.getLeague());
        gameDto.setRound(Long.valueOf(result.getRound()));
        return gameDto;
    }
}
