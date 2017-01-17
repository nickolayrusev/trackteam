package com.nrusev.service;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.nrusev.repository.TeamPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Service
public class TeamPoolService {
    private final TeamPoolRepository teamPoolRepository;

    private final User user;

    @Autowired
    public TeamPoolService(TeamPoolRepository teamPoolRepository, UserService userService, User user) {
        this.teamPoolRepository = teamPoolRepository;
        this.user = user;
    }

    @Transactional
    public TeamPool save(TeamPool teamPool){
        teamPool.setUser(user);
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
