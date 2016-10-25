package com.nrusev.service;

import com.nrusev.domain.TeamPool;
import com.nrusev.repository.TeamPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public TeamPool save(TeamPool teamPool){
        return this.teamPoolRepository.save(teamPool);
    }

    public List<TeamPool> findAllByUserName(String userName){
        return this.teamPoolRepository.findAllByUserName(userName);
    }
}
