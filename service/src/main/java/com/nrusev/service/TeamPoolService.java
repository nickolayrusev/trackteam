package com.nrusev.service;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.repository.TeamPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Service
public class TeamPoolService {
    private final TeamPoolRepository teamPoolRepository;

    @Autowired
    public TeamPoolService(TeamPoolRepository teamPoolRepository) {
        this.teamPoolRepository = teamPoolRepository;
    }

    @Transactional
    public TeamPool save(TeamPool teamPool){
        return this.teamPoolRepository.save(teamPool);
    }

    public List<TeamPool> findAllByUserName(String userName){
        return this.teamPoolRepository.findAllByUserName(userName);
    }

    @Transactional
    public TeamPool addTeam(TeamPool pool, Team team){
        pool.getTeams().add(team);
        return save(pool);
    }

    @Transactional
    public TeamPool removeTeam(TeamPool pool, Team team){
        pool.getTeams().remove(team);
        return save(pool);
    }


}
