package com.nrusev.exchange.impl;

import com.nrusev.enums.SeasonKeys;

import java.util.Date;

/**
 * Created by Nikolay Rusev on 22.2.2017 г..
 */
public class GameDto {
    private String homeTeam;
    private String visitorTeam;
    private Long homeTeamGoals;
    private Long visitorTeamGoals;
    private Date playAt;
    private SeasonKeys seasonKey;
    private Long round;
    private String league;

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Long getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Long homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Long getVisitorTeamGoals() {
        return visitorTeamGoals;
    }

    public void setVisitorTeamGoals(Long visitorTeamGoals) {
        this.visitorTeamGoals = visitorTeamGoals;
    }

    public Date getPlayAt() {
        return playAt;
    }

    public void setPlayAt(Date playAt) {
        this.playAt = playAt;
    }

    public SeasonKeys getSeasonKey() {
        return seasonKey;
    }

    public void setSeasonKey(SeasonKeys seasonKey) {
        this.seasonKey = seasonKey;
    }

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }
}
