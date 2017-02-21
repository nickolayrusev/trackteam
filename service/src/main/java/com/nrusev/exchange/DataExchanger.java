package com.nrusev.exchange;

import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.enums.SeasonKeys;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikolay Rusev on 9.2.2017 г..
 */
public interface DataExchanger {
    List<Game> findTodayGames();
    List<Game> findGameByDate(Date from, Date to);
    List<Game> getFixturesByLeagueAndSeason(String league, SeasonKeys season);
    Set<Team> todaysTeams();
}
