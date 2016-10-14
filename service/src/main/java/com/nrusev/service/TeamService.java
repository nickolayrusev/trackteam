package com.nrusev.service;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import info.debatty.java.stringsimilarity.JaroWinkler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    public List<Team> findAllClubTeams() {
        return teamRepository.findAllClubTeams();
    }

    public List<Team> findByTitleIgnoreCase(String title) {
        return teamRepository.findByTitleIgnoreCase(title);
    }

    public List<Team> findByCountryName(String countryName) {
        return teamRepository.findByCountryNameAndClubIsTrueOrderByTitle(countryName);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> findTeam(String name, String... countries) {
        List<Team> byCountryName = findByCountries(countries);

        Optional<Team> teamByName = byCountryName.stream().filter(t -> isTeamSame(name,t)).findAny();
        if (teamByName.isPresent())
            return teamByName;

        return Optional.empty();
    }

    public boolean isTeamSame(String candidate, Team original) {
        if(original.getTitle().equalsIgnoreCase(candidate))
            return true;
        if(original.getSynonyms().contains(candidate))
            return true;
        if(original.getKey().equalsIgnoreCase(candidate))
            return true;
        return false;
    }

    public List<Team> findUKTeams(){
        return findByCountries("England","Wales");
    }

    public List<Team> findByCountries(String ... names){
        return teamRepository.findByCountriesNames(names);
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
