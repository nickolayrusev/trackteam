package com.nrusev.service;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import info.debatty.java.stringsimilarity.JaroWinkler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    public List<Team> findByCountryName(String countryName){
        return teamRepository.findByCountryNameAndClubIsTrueOrderByTitle(countryName);
    }

    public Team save(Team team){
        return teamRepository.save(team);
    }

    public Optional<Team> findTeam(String name, String country){
        List<Team> byCountryName =  findByCountryName(country);

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

    public Optional<Team> fuzzyMatch(List<Team> byCountryName, String candidate){
        JaroWinkler d = new JaroWinkler();

        List<Team> collect = byCountryName.stream().filter(t -> d.distance(t.getTitle(), candidate) < .15).collect(toList());
        if(collect.size() > 1)
            return collect.stream().sorted((t,q)-> Double.compare(d.distance(t.getTitle(), candidate),  d.distance(q.getTitle(),candidate) )).findFirst();

        if(collect.size() == 1)
            return Optional.of(collect.get(0));
        return Optional.empty();
    }
}
