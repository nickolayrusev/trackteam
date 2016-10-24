package com.nrusev.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Entity
@Table(name = "AppUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    @OneToMany
    @JoinColumn(name="user_id") // join column is in table for Order
    private Set<TeamSet> teamSets = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<TeamSet> getTeamSets() {
        return teamSets;
    }

    public void setTeamSets(Set<TeamSet> teamSets) {
        this.teamSets = teamSets;
    }
}
