package com.nrusev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@Entity
@Table(uniqueConstraints=
@UniqueConstraint(columnNames = {"name"}))
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String code;

    private String shortName;

    private String crestUrl;

    private String squadMarketValue;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teams")
    private Set<Pool> pools;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teams")
    private Set<Season> seasons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Pool> getPools() {
        return pools;
    }

    public void setPools(Set<Pool> pools) {
        this.pools = pools;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }
}
