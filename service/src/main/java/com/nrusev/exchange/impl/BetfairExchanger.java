package com.nrusev.exchange.impl;

import com.jbetfairng.BetfairClient;
import com.jbetfairng.entities.Event;
import com.jbetfairng.entities.EventResult;
import com.jbetfairng.entities.MarketFilter;
import com.jbetfairng.entities.TimeRange;
import com.nrusev.config.ExchangeConfig;
import com.nrusev.domain.Game;
import com.nrusev.exchange.DataExchanger;
import com.nrusev.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Nikolay Rusev on 9.2.2017 г..
 */
@Component
public class BetfairExchanger implements DataExchanger {

    private final BetfairClient client;
    private final Set<ExchangeConfig.Competition> competitions;
    private final Set<ExchangeConfig.Competition> supportedCompetitions;
    private final TeamService teamService;


    private static final ZoneOffset UTC = ZoneOffset.UTC;

    @Autowired
    public BetfairExchanger(BetfairClient client,
                            @Qualifier("competitions") Set<ExchangeConfig.Competition> competitions,
                            @Qualifier("supportedCompetitions") Set<ExchangeConfig.Competition> supoportedCompetitions,
                            TeamService teamService) {
        this.client = client;
        this.competitions = competitions;
        this.supportedCompetitions = supoportedCompetitions;
        this.teamService = teamService;
    }

    @Override
    public List<Game> findTodayGames() {
        MarketFilter filter = new MarketFilter();
        filter.setCompetitionIds(supportedCompetitions.stream().map(c->String.valueOf(c.getId())).collect(toSet()));

        ZonedDateTime now = ZonedDateTime.now(UTC);
        ZonedDateTime from = ZonedDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0,0,0,UTC);
        ZonedDateTime to = from.plus(1439, ChronoUnit.MINUTES);

        System.out.println("from : " + from + " to : " + to);
        TimeRange range = new TimeRange();
        range.setFrom(Date.from(from.toInstant()));
        range.setTo(Date.from(to.toInstant()));

        filter.setMarketStartTime(range);
        List<EventResult> response = client.listEvents(filter).getResponse();
        response.forEach(eventResult -> {
            toGame(eventResult.getEvent());
        });
        return Collections.emptyList();
    }

    private Game toGame(Event event){
        String[] split = event.getName().split(" v ");
        if(split.length == 2) {
            String homeTeam = split[0], visitorTeam = split[1];
            System.out.println("home : "+ homeTeam + " visitor: "+visitorTeam);
            final String countryCode = event.getCountryCode();
            teamService.findTeamByCountryAlpha2Code(homeTeam, countryCode).ifPresent(s -> {
                System.out.println(" team is found " + s.getTitle());
            });
            teamService.findTeamByCountryAlpha2Code(visitorTeam, countryCode).ifPresent(s -> {
                System.out.println(" team is found " + s.getTitle());
            });
        }
        return null;
    }
}
