package com.nrusev.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Entity
public class TeamSet {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   private Boolean closed;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(name = "team_set_teams",
           joinColumns = {
           @JoinColumn(name = "team_set_id", nullable = false, updatable = false) },
           inverseJoinColumns = {
              @JoinColumn(name = "team_id",
                   nullable = false, updatable = false) })
   private Set<Team> teams = new HashSet<Team>(0);

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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Set<Team> getTeams() {
      return teams;
   }

   public void setTeams(Set<Team> teams) {
      this.teams = teams;
   }

   public Boolean getClosed() {
      return closed;
   }

   public void setClosed(Boolean closed) {
      this.closed = closed;
   }
   @Override
   public String toString() {
      return "TeamSet{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              '}';
   }
}
