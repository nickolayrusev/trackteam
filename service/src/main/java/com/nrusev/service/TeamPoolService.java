package com.nrusev.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.nrusev.repository.TeamPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Service
public class TeamPoolService {
    private final TeamPoolRepository teamPoolRepository;
    private final User user;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public TeamPoolService(TeamPoolRepository teamPoolRepository, User user, UserService userService, GameService gameService) {
        this.teamPoolRepository = teamPoolRepository;
        this.user = user;
        this.userService = userService;
        this.gameService = gameService;
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
        Set<Team> teams = new HashSet<>(pool.getTeams());
        teams.add(team);
        pool.setTeams(teams);
        return save(pool);
    }

    public TeamPool findById(Long id){
        return this.teamPoolRepository.findOne(id);
    }

    @Transactional
    public void delete(TeamPool pool){
        this.teamPoolRepository.delete(pool);
    }

    @Transactional
    public TeamPool removeTeam(TeamPool pool, Team team){
        pool.getTeams().remove(team);
        return save(pool);
    }

    public Map<Game, Collection<TeamPool>> findUserGames(){
        Multimap<Game,TeamPool> map =  ArrayListMultimap.create();
        User user = userService.findByUserName("nrusev").orElseThrow(IllegalStateException::new);
        List<Game> todaysGames = this.gameService.findTodaysGames();
        List<TeamPool> allByUserName = this.findAllByUserName(user.getUserName());

        todaysGames.forEach(g->{
            allByUserName.forEach(pool -> {
                if(pool.getTeams().contains(g.getHomeTeam()) || pool.getTeams().contains(g.getVisitorTeam())){
                    map.put(g,pool);
                }
            });
        });

        return map.asMap();
    }


}
