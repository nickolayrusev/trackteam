package com.nrusev.service;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Created by nikolayrusev on 2/24/16.
 */
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final EntityManager em;

    @Autowired
    public TeamService(TeamRepository teamRepository, JpaContext jpaContext) {
        this.teamRepository = teamRepository;
        this.em = jpaContext.getEntityManagerByManagedType(Team.class);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findAllClubTeams(){
        return teamRepository.findAllClubTeams();
    }

    public List<Team> findByTitleIgnoreCase(String title){
        return teamRepository.findByTitleIgnoreCase(title);
    }

    public Team save(Team team){
        return this.teamRepository.save(team);
    }

    public Optional<Team> findTeam(String name, String country){
        List<Team> byCountryName = teamRepository.findByCountryName(country);

        //exact match by tittle and country
        Optional<Team> teamByName = byCountryName.stream().filter(t -> t.getTitle().equalsIgnoreCase(name)).findAny();
        if(teamByName.isPresent())
            return teamByName;

        //match by synonym
        Optional<Team> teamBySynonym = byCountryName.stream().filter(t -> t.getSynonyms().contains(name)).findAny();
        if(teamBySynonym.isPresent())
            return teamBySynonym;

        //match by key
        Optional<Team> teamByKey = byCountryName.stream().filter(t -> name.equalsIgnoreCase(t.getKey())).findAny();
        if(teamByKey.isPresent())
            return teamByKey;

        return Optional.empty();
    }
}
