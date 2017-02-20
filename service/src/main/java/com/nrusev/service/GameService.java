package com.nrusev.service;

import com.nrusev.domain.*;
import com.nrusev.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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

    public List<Game> findTodaysGames(){
//        return this.gameRepository.findAllClub("2014/15","en","England").stream().filter(g->g.getRound().getTitle().equalsIgnoreCase("Matchday 38")).collect(toList());
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime from = ZonedDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0,0,0,ZoneOffset.UTC);
        ZonedDateTime to = from.plus(Duration.ofHours(23).toMinutes() + 59, ChronoUnit.MINUTES);
        return this.gameRepository.findAllGamesByDate(Date.from(from.toInstant()), Date.from(to.toInstant()));
    }

    public Game save(Game game, Round round, Event event, Season season, League league){

        return this.gameRepository.save(game);
    }
}
