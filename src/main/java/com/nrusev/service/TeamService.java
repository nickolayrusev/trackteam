package com.nrusev.service;

import com.nrusev.domain.Season;
import com.nrusev.domain.Team;
import com.nrusev.repository.SeasonRepository;
import com.nrusev.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikolayrusev on 2/24/16.
 */
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository,SeasonRepository seasonRepository){
        this.teamRepository = teamRepository;
        this.seasonRepository = seasonRepository;
    }

    @Transactional
    public void addTeamToSeason(Long teamId,Long seasonId){
        Team team = teamRepository.findOne(teamId);
        Season season = seasonRepository.findOne(seasonId);

        season.getTeams().add(team);
        seasonRepository.save(season);
    }

}
