package com.nrusev.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Entity
public class TeamPool extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   private Boolean closed;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", nullable = false)
   private User user;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "team_pool_teams",
           joinColumns = {
           @JoinColumn(name = "team_pool_id", nullable = false, updatable = false) },
           inverseJoinColumns = {
              @JoinColumn(name = "team_id",
                   nullable = false, updatable = false) })
   @OrderBy("title")
   private Set<Team> teams = new HashSet<Team>(0);

   public TeamPool() {

   }

   public TeamPool(String name, String description, Boolean closed) {
      this.name = name;
      this.description = description;
      this.closed = closed;
   }

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

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   @Override
   public String toString() {
      return "TeamPool{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              '}';
   }

}
