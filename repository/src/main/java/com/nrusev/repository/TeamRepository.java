package com.nrusev.repository;

import com.nrusev.domain.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by nikolayrusev on 2/22/16.
 */
public interface TeamRepository  extends CrudRepository<Team,Long>{
    List<Team> findByTitle(String title);
    List<Team> findByTitleIgnoreCase(String title);
    List<Team> findByCountryName(String name);
//    SELECT t.*
//    FROM   teams t
//    INNER JOIN events_teams et
//    ON et.team_id = t.id
//    INNER JOIN events e
//    ON et.event_id = e.id
//    INNER JOIN leagues l
//    ON l.id = e.league_id
//    INNER JOIN seasons s
//    ON s.id = e.season_id
//    where s.key = '2013/14' and l.key = 'en';

    @EntityGraph(value = "team.country", type = EntityGraph.EntityGraphType.LOAD)
    List<Team> findAll();

    @Query("select t from Team t join fetch t.country where t.club = true and t.national = false order by t.title")
    List<Team> findAllClubTeams();

    @Query("select t from Team t " +
    		"join fetch t.country cou " +
            "join fetch cou.continent cn " +
            "where cn.name = :name and t.national = :isNational and t.club = :isClub")
    List<Team> findByContinentName(@Param("name") String name, @Param("isNational") boolean isNational, @Param("isClub") boolean isClub);
    
    @Query("select t from Team t join t.events e join e.league l join e.season s where s.key = :seasonKey and l.key = :leagueKey")
    List<Team> findBySeasonKeyAndLeagueKey(@Param("seasonKey") String seasonKey, @Param("leagueKey") String leagueKey);
}
