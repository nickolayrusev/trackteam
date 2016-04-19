package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "events")
public class Event {
    private Long id;
    private String key;
    private League league;
//    private Long leagueId;
//    private Long seasonId;
    private Season season;
    private Date startAt;
    private Date endAt;
    private Boolean team3;
    private String sources;
    private String config;
    private Date createdAt;
    private Date updatedAt;
    private Set<Team> teams = new HashSet<>(0);

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "key", nullable = false, length = -1)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    @Basic
//    @Column(name = "league_id", nullable = false)
//    public Long getLeagueId() {
//        return leagueId;
//    }
//
//    public void setLeagueId(Long leagueId) {
//        this.leagueId = leagueId;
//    }

//    @Basic
//    @Column(name = "season_id", nullable = false)
//    public Long getSeasonId() {
//        return seasonId;
//    }
//
//    public void setSeasonId(Long seasonId) {
//        this.seasonId = seasonId;
//    }

    @Basic
    @Column(name = "start_at", nullable = false)
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @Basic
    @Column(name = "end_at", nullable = true)
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @Basic
    @Column(name = "team3", nullable = false)
    public Boolean getTeam3() {
        return team3;
    }

    public void setTeam3(Boolean team3) {
        this.team3 = team3;
    }

    @Basic
    @Column(name = "sources", nullable = true, length = -1)
    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    @Basic
    @Column(name = "config", nullable = true, length = -1)
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @ManyToMany(mappedBy="events")
    public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "league_id", nullable = false)
	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
  
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "season_id", nullable = false)
	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (key != null ? !key.equals(event.key) : event.key != null) return false;
//        if (leagueId != null ? !leagueId.equals(event.leagueId) : event.leagueId != null) return false;
//        if (seasonId != null ? !seasonId.equals(event.seasonId) : event.seasonId != null) return false;
        if (startAt != null ? !startAt.equals(event.startAt) : event.startAt != null) return false;
        if (endAt != null ? !endAt.equals(event.endAt) : event.endAt != null) return false;
        if (team3 != null ? !team3.equals(event.team3) : event.team3 != null) return false;
        if (sources != null ? !sources.equals(event.sources) : event.sources != null) return false;
        if (config != null ? !config.equals(event.config) : event.config != null) return false;
        if (createdAt != null ? !createdAt.equals(event.createdAt) : event.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(event.updatedAt) : event.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
//        result = 31 * result + (leagueId != null ? leagueId.hashCode() : 0);
//        result = 31 * result + (seasonId != null ? seasonId.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (team3 != null ? team3.hashCode() : 0);
        result = 31 * result + (sources != null ? sources.hashCode() : 0);
        result = 31 * result + (config != null ? config.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
