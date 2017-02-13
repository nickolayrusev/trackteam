package com.nrusev.exchange;

import com.nrusev.domain.Game;

import java.util.List;

/**
 * Created by Nikolay Rusev on 9.2.2017 г..
 */
public interface DataExchanger {
    List<Game> findTodayGames();
}
