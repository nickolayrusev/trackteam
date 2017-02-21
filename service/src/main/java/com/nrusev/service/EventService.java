package com.nrusev.service;

import com.nrusev.domain.Event;
import com.nrusev.domain.League;
import com.nrusev.domain.Season;
import com.nrusev.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay Rusev on 21.2.2017 г..
 */
@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    List<Event> findByLeagueAndSeason(League league, Season season){
        return this.eventRepository.findByLeagueAndSeason(league, season);
    }
}
