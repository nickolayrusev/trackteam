package com.nrusev.exchange;

import com.nrusev.enums.LeagueKeys;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.exchange.impl.GameDto;

import java.util.Date;
import java.util.List;

/**
 * Created by Nikolay Rusev on 9.2.2017 г..
 */
public interface DataExchanger {
    List<GameDto> findTodayGames();
    List<GameDto> findGameByDate(Date from, Date to);
    List<GameDto> getFixturesByLeagueAndSeason(LeagueKeys league, SeasonKeys season);
}
