package com.nrusev.service;

import com.nrusev.domain.*;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.exchange.impl.Competition;
import com.nrusev.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private final TeamService teamService;
    private List<Competition> supportedCompetitions;

    @Autowired
    public GameService(GameRepository gameRepository, LeagueService leagueService,
                       SeasonService seasonService, RoundService roundService, EventService eventService,
                       TeamService teamService, List<Competition> supportedCompetitions) {
        this.gameRepository = gameRepository;
        this.leagueService = leagueService;
        this.seasonService = seasonService;
        this.roundService = roundService;
        this.eventService = eventService;
        this.teamService = teamService;
        this.supportedCompetitions = supportedCompetitions;
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
        LocalDate now = LocalDate.now(ZoneOffset.UTC);
        Instant from = now.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant to = now.atStartOfDay().plus(23,ChronoUnit.HOURS).plus(59,ChronoUnit.MINUTES).toInstant(ZoneOffset.UTC);
        System.out.println("from : " + from + " to : " + to);
        return this.gameRepository.findAllGamesByDate(Date.from(from), Date.from(to));
    }

    public List<Game> findGamesByDate(LocalDate date){
        Instant from = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant to = date.atStartOfDay().plus(23,ChronoUnit.HOURS).plus(59,ChronoUnit.MINUTES).toInstant(ZoneOffset.UTC);
       return this.gameRepository.findAllGamesByDate(Date.from(from),Date.from(to));
    }

    @Transactional
    public Game save(Long round, SeasonKeys season, String league,
                     String homeTeam, String visitorTeam, Long homeTeamGoals,
                        Long awayTeamGoals, Date playAt, Long pos ){
        Assert.notNull(round);
        Assert.notNull(season);
        Assert.notNull(league);

        Game game = new Game();
        leagueService.findByLeagueTitle(league).stream().findFirst().ifPresent(l->{
           seasonService.findByTitle(season).stream().findFirst().ifPresent(s->{
              eventService.findByLeagueAndSeason(l,s).stream().findFirst().ifPresent(e->{
                    game.setRound(roundService.findByEventAndPosition(e,round).stream().findFirst().orElse(addNewRound(e,round)));
              });
           });
        });
        game.setPlayAt(playAt);
        findCompetition(league).ifPresent(c->{
            teamService.findTeamByCountryAlpha2Code(homeTeam,c.getRegion()).ifPresent(game::setHomeTeam);
            teamService.findTeamByCountryAlpha2Code(visitorTeam,c.getRegion()).ifPresent(game::setVisitorTeam);
        });
       game.setScore1(homeTeamGoals);
       game.setScore2(awayTeamGoals);
       game.setKnockout(false);
       game.setHome(true);
       game.setWinner(winner(homeTeamGoals,awayTeamGoals));
       game.setWinner90(winner(homeTeamGoals,awayTeamGoals));
       game.setPos(pos);
       game.setPostponed(false);
       return this.gameRepository.save(game);
    }

    private Optional<Competition> findCompetition(String leagueName){
       return supportedCompetitions.stream().filter(c->c.getName().equals(leagueName)).findFirst();
    }

    private Round addNewRound(Event event, Long position){
        Round round = new Round();
        round.setEvent(event);
        round.setPos(position);
        round.setKnockout(false);
        round.setAuto(false);
        round.setStartAt(event.getStartAt());
        round.setEndAt(event.getEndAt());
        round.setTitle("Matchday " + position);
        return this.roundService.save(round);
    }

    private Long winner(Long home, Long away){
        if(home == null || away == null)
            return null;
        if(away.equals(home))
            return 0L;
       return  (home > away) ? 1L : 2L;
    }

}
