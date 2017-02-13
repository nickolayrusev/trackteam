package com.nrusev.exchange.impl;

import com.jbetfairng.BetfairClient;
import com.jbetfairng.entities.EventResult;
import com.jbetfairng.entities.MarketFilter;
import com.jbetfairng.entities.TimeRange;
import com.nrusev.domain.Game;
import com.nrusev.exchange.DataExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Nikolay Rusev on 9.2.2017 г..
 */
@Component
public class BetfairExchanger implements DataExchanger {

    private final BetfairClient client;

    private static final ZoneOffset UTC = ZoneOffset.UTC;

    @Autowired
    public BetfairExchanger(BetfairClient client) {
        this.client = client;
    }

    @Override
    public List<Game> findTodayGames() {
        MarketFilter filter = new MarketFilter();
        filter.setCompetitionIds(new HashSet<String>(){{
            add("55");
            add("57");
            add("81");
            add("31");
            add("35");
            add("37");
            add("99");
        }});


        ZonedDateTime now = ZonedDateTime.now(UTC);
        ZonedDateTime from = ZonedDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0,0,0,UTC);
        ZonedDateTime to = from.plus(1439, ChronoUnit.MINUTES);

        System.out.println("from : " + from + " to : " + to);
        TimeRange range = new TimeRange();
        range.setFrom(Date.from(from.toInstant()));
        range.setTo(Date.from(to.toInstant()));

        filter.setMarketStartTime(range);
        List<EventResult> response = client.listEvents(filter).getResponse();

        return Collections.emptyList();
    }
}
