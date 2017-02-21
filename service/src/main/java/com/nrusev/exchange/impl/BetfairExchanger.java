package com.nrusev.exchange.impl;

import com.jbetfairng.BetfairClient;
import com.jbetfairng.entities.Event;
import com.jbetfairng.entities.EventResult;
import com.jbetfairng.entities.MarketFilter;
import com.jbetfairng.entities.TimeRange;
import com.nrusev.config.BetfairCompetitionsConfig;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.exchange.DataExchanger;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Nikolay Rusev on 9.2.2017 г..
 */
@Component
public class BetfairExchanger implements DataExchanger {

    private final BetfairClient client;
    private final Set<Competition> competitions;
    private final Set<Competition> supportedCompetitions;
    private final TeamService teamService;


    private static final ZoneOffset UTC = ZoneOffset.UTC;

    @Autowired
    public BetfairExchanger(BetfairClient client,
                            @Qualifier("competitions") Set<Competition> competitions,
                            @Qualifier("supportedCompetitions") Set<Competition> supportedCompetitions,
                            TeamService teamService) {
        this.client = client;
        this.competitions = competitions;
        this.supportedCompetitions = supportedCompetitions;
        this.teamService = teamService;
    }

    @Override
    public List<Game> findTodayGames() {
        MarketFilter filter = new MarketFilter();
        filter.setCompetitionIds(supportedCompetitions.stream().map(c->String.valueOf(c.getId())).collect(toSet()));

        ZonedDateTime now = ZonedDateTime.now(UTC);
        ZonedDateTime from = ZonedDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0,0,0,UTC);
        ZonedDateTime to = from.plus(Duration.ofHours(23).toMinutes() + 59, ChronoUnit.MINUTES);
//        ZonedDateTime to =  from.plus(Duration.of(4,ChronoUnit.DAYS).toMinutes(), ChronoUnit.MINUTES);

        System.out.println("from : " + from + " to : " + to);
        TimeRange range = new TimeRange();
        range.setFrom(Date.from(from.toInstant()));
        range.setTo(Date.from(to.toInstant()));

        filter.setMarketStartTime(range);
        List<EventResult> response = client.listEvents(filter).getResponse();
        return response.stream().map(r-> toGame(r.getEvent())).filter(Objects::nonNull).collect(toList());
    }

    @Override
    public List<Game> findGameByDate(Date from, Date to) {
        return null;
    }

    @Override
    public List<Game> getFixturesByLeagueAndSeason(String league, SeasonKeys season) {
        return null;
    }

    @Override
    public Set<Team> todaysTeams() {
        return null;
    }

    private Game toGame(Event event){
        String[] split = event.getName().split(" v ");
        if(split.length == 2) {
            Game game = new Game();
            String homeTeam = split[0], visitorTeam = split[1];
            System.out.println("home : "+ homeTeam + " visitor: "+visitorTeam + " date : "+event.getOpenDate());
            final String countryCode = event.getCountryCode();
            teamService.findTeamByCountryAlpha2Code(homeTeam, countryCode).ifPresent(s -> {
                System.out.println(" team is found " + s.getTitle());
                game.setHomeTeam(s);
            });
            teamService.findTeamByCountryAlpha2Code(visitorTeam, countryCode).ifPresent(s -> {
                System.out.println(" team is found " + s.getTitle());
                game.setVisitorTeam(s);
            });
            game.setPlayAt(event.getOpenDate());
            return game;
        }
        return null;
    }
}
