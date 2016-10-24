package com.nrusev.service;

import com.nrusev.domain.TeamSet;
import com.nrusev.repository.TeamSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Service
public class TeamSetService {
    private final TeamSetRepository teamSetRepository;

    @Autowired
    public TeamSetService(TeamSetRepository teamSetRepository) {
        this.teamSetRepository = teamSetRepository;
    }

    public TeamSet save(TeamSet teamSet){
        return this.teamSetRepository.save(teamSet);
    }
}
