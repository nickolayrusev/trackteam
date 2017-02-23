package com.nrusev.repository;

import com.nrusev.domain.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

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

    @Query("select g from Game g " +
            "join fetch g.homeTeam " +
            "join fetch g.visitorTeam " +
            "join fetch g.round r " +
            "join fetch r.event e " +
            "join fetch e.season s " +
            "join fetch e.league l " +
            "join fetch l.country c " +
            "where l.club='t' and  s.key = :seasonKey and l.key = :leagueKey and c.name=:country")
    List<Game> findAllClub(@Param("seasonKey") String seasonKey, @Param("leagueKey") String leagueKey, @Param("country") String country);

    @Query("select g from Game g " +
            "join fetch g.homeTeam " +
            "join fetch g.visitorTeam " +
            "join fetch g.round r  " +
            "where g.winner is not null " +
            "and ( (g.homeTeam.title =:firstTeam AND g.visitorTeam.title =:secondTeam) " +
            "or ( g.visitorTeam.title =:firstTeam AND g.homeTeam.title = :secondTeam) ) order by g.playAt desc" )
    List<Game> findAllHeadToHead(@Param("firstTeam") String firstTeam, @Param("secondTeam") String secondTeam);

    @Query("select g from Game g join fetch g.homeTeam join fetch g.visitorTeam join fetch g.round r " +
            "where g.winner is not null and (g.homeTeam.id = :teamId or g.visitorTeam.id = :teamId) order by g.playAt desc")
    List<Game> findAllGamesByTeam(@Param("teamId") Long teamId);

    @Query("select g from Game g " +
            "join fetch g.homeTeam " +
            "join fetch g.visitorTeam " +
            "join fetch g.round r " +
            "join fetch r.event e " +
            "join fetch e.season s " +
            "join fetch e.league l " +
            "where g.playAt between :from and :to order by g.playAt asc")
    List<Game> findAllGamesByDate(@Temporal(TemporalType.DATE) @Param("from") Date from, @Temporal(TemporalType.DATE) @Param("to")Date to);

}

