package com.nrusev.service;

import com.nrusev.domain.*;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Date;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final LeagueService leagueService;
    private final SeasonService seasonService;
    private final RoundService roundService;
    private final EventService eventService;

    @Autowired
    public GameService(GameRepository gameRepository, LeagueService leagueService,
                       SeasonService seasonService, RoundService roundService, EventService eventService) {
        this.gameRepository = gameRepository;
        this.leagueService = leagueService;
        this.seasonService = seasonService;
        this.roundService = roundService;
        this.eventService = eventService;
    }

    public Game findById(Long id){
        return this.gameRepository.findOne(id);
    }

    public List<Game> findAllClub(String seasonKey, String leagueKey, String country){
        return this.gameRepository.findAllClub(seasonKey,leagueKey,country);
    }

    public List<Game> findAllHeadToHead(String firstTeam, String secondTeam){
        return this.gameRepository.findAllHeadToHead(firstTeam,secondTeam);
    }

    public List<Game> findGamesByTeam(Long id){
        return this.gameRepository.findAllGamesByTeam(id);
    }

    //TODO: ugly writtern needs refactoring. Do not get todays games from exchanger. Get them from db
    public List<Game> findTodaysGames(){
//        return this.gameRepository.findAllClub("2014/15","en","England").stream().filter(g->g.getRound().getTitle().equalsIgnoreCase("Matchday 38")).collect(toList());
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime from = ZonedDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0,0,0,ZoneOffset.UTC);
        ZonedDateTime to = from.plus(Duration.ofHours(23).toMinutes() + 59, ChronoUnit.MINUTES);
        return this.gameRepository.findAllGamesByDate(Date.from(from.toInstant()), Date.from(to.toInstant()));
    }

    @Transactional
    public Game save(Game game, Long round, SeasonKeys season, String league  ){
        Assert.notNull(game);
        Assert.notNull(round);
        Assert.notNull(season);
        Assert.notNull(league);
        Assert.notNull(game.getHomeTeam());
        Assert.notNull(game.getVisitorTeam());
        Assert.notNull(game.getPlayAt());

        List<League> byLeagueTitle = leagueService.findByLeagueTitle(league);
        List<Season> byTitle = seasonService.findByTitle(season);

        byLeagueTitle.stream().findFirst().ifPresent(l->{
            byTitle.stream().findFirst().ifPresent(s->{
                this.eventService.findByLeagueAndSeason(l, s).stream().findFirst().ifPresent(e->{
                    Round round1 = this.roundService.findByEventAndPosition(e, round).stream().findFirst()
                            .orElse(buildNew(e, round));
                    game.setRound(round1);
                });
            });
        });

        return this.gameRepository.save(game);
    }

    private Round buildNew(Event event, Long position){
        Round r = new Round();
        r.setEvent(event);
        r.setPos(position);
        r.setKnockout(false);
        return this.roundService.save(r);
    }

}
