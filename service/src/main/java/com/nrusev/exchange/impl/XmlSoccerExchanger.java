package com.nrusev.exchange.impl;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.exchange.DataExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikolay Rusev on 16.2.2017 г..
 */
@Component
public class XmlSoccerExchanger implements DataExchanger {
    private final XmlSoccerService client;

    @Autowired
    public XmlSoccerExchanger(XmlSoccerService client) {
        this.client = client;
    }

    @Override
    public List<Game> findTodayGames() {
//         client.getAllLeagues().forEach(System.out::println);
        client.getFixturesByDateInterval("2017-02-16","2017-02-28").forEach(System.out::println);
         return Collections.emptyList();
    }

    @Override
    public List<Game> findGameByDate(Date from, Date to) {
        return null;
    }

    @Override
    public Set<Team> todaysTeams() {
        return null;
    }
}
