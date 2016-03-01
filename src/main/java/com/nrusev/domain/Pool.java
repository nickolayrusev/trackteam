package com.nrusev.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@Entity
public class Pool {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_pool",  joinColumns = {
            @JoinColumn(name = "pool_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "team_id",
                    nullable = false, updatable = false) })
    private Set<Team> teams = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

}
