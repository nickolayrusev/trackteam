package com.nrusev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints=
@UniqueConstraint(columnNames = {"year", "league"}))
public class Season {
    @Id
    private Long id;
    private Integer year;
    private String caption;
    private String league;
    private Integer numberOfTeams;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "season_team",  joinColumns = {
            @JoinColumn(name = "season_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "team_id",
                    nullable = false, updatable = false) })
    private Set<Team> teams = new HashSet<>(0);

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
