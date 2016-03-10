package com.nrusev.service;

import com.nrusev.domain.Pool;
import com.nrusev.domain.Season;
import com.nrusev.domain.Team;
import com.nrusev.repository.PoolRepository;
import com.nrusev.repository.SeasonRepository;
import com.nrusev.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nikolayrusev on 2/24/16.
 */
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;
    private final PoolRepository poolRepository;
    private final JpaContext jpaContext;

    @Autowired
    public TeamService(TeamRepository teamRepository, SeasonRepository seasonRepository,
                       JpaContext jpaContext, PoolRepository poolRepository){
        this.teamRepository = teamRepository;
        this.seasonRepository = seasonRepository;
        this.jpaContext = jpaContext;
        this.poolRepository = poolRepository;
    }

    @Transactional
    public void addTeamToSeason(Long teamId,Long seasonId){
        Team team = teamRepository.findOne(teamId);
        Season season = seasonRepository.findOne(seasonId);

        season.getTeams().add(team);
        seasonRepository.save(season);
    }

    @Transactional
    public void addTeamToPool(Long teamId,Long poolId){
        Team team = teamRepository.findOne(teamId);
        Pool pool = poolRepository.findOne(poolId);

        pool.getTeams().add(team);
        poolRepository.save(pool);
    }

    @Transactional(readOnly = true)
    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public List<Team> findAllPremierShipTeams(){
        return jpaContext.getEntityManagerByManagedType(Team.class).createQuery("select t from Team t",Team.class).getResultList();
    }

}
