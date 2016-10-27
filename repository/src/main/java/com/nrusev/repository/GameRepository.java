package com.nrusev.repository;

import com.nrusev.domain.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
public interface GameRepository extends CrudRepository<Game, Long> {
//    SELECT g.id,
//    t.title,
//    t2.title,
//    g.score1,
//    g.score2,
//    g.play_at,
//    r.title as round_title,
//    l.title as league_title,
//    s.title as season_title,
//    c.name as country_name
//    FROM games g
//    INNER JOIN teams t ON t.id = g.team1_id
//    INNER JOIN teams t2 ON t2.id = g.team2_id
//    INNER JOIN rounds r ON r.id = g.round_id
//    inner join events e on e.id = r.event_id
//    INNER JOIN seasons s ON e.season_id = s.id
//    INNER JOIN leagues l ON l.id = e.league_id
//    inner join countries c on c.id = l.country_id
//    where l.club = 't' and c.name ='England' and s.title='2015/16';
}

