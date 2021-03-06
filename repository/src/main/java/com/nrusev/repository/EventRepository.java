package com.nrusev.repository;

import com.nrusev.domain.Event;
import com.nrusev.domain.League;
import com.nrusev.domain.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nikolayrusev on 2/20/17.
 */
public interface EventRepository extends CrudRepository<Event, Long>{
    List<Event> findByLeagueAndSeason(League league, Season season);
}
