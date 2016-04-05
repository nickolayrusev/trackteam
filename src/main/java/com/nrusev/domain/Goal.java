package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "goals")
public class Goal {
    private Long id;
    private Long personId;
    private Long gameId;
    private Long teamId;
    private Long minute;
    private Long offset;
    private Long score1;
    private Long score2;
    private Boolean penalty;
    private Boolean owngoal;
    private Date createdAt;
    private Date updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_id", nullable = false)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "game_id", nullable = false)
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "team_id", nullable = false)
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "minute", nullable = true)
    public Long getMinute() {
        return minute;
    }

    public void setMinute(Long minute) {
        this.minute = minute;
    }

    @Basic
    @Column(name = "offset", nullable = false)
    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    @Basic
    @Column(name = "score1", nullable = true)
    public Long getScore1() {
        return score1;
    }

    public void setScore1(Long score1) {
        this.score1 = score1;
    }

    @Basic
    @Column(name = "score2", nullable = true)
    public Long getScore2() {
        return score2;
    }

    public void setScore2(Long score2) {
        this.score2 = score2;
    }

    @Basic
    @Column(name = "penalty", nullable = false)
    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    @Basic
    @Column(name = "owngoal", nullable = false)
    public Boolean getOwngoal() {
        return owngoal;
    }

    public void setOwngoal(Boolean owngoal) {
        this.owngoal = owngoal;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (id != null ? !id.equals(goal.id) : goal.id != null) return false;
        if (personId != null ? !personId.equals(goal.personId) : goal.personId != null) return false;
        if (gameId != null ? !gameId.equals(goal.gameId) : goal.gameId != null) return false;
        if (teamId != null ? !teamId.equals(goal.teamId) : goal.teamId != null) return false;
        if (minute != null ? !minute.equals(goal.minute) : goal.minute != null) return false;
        if (offset != null ? !offset.equals(goal.offset) : goal.offset != null) return false;
        if (score1 != null ? !score1.equals(goal.score1) : goal.score1 != null) return false;
        if (score2 != null ? !score2.equals(goal.score2) : goal.score2 != null) return false;
        if (penalty != null ? !penalty.equals(goal.penalty) : goal.penalty != null) return false;
        if (owngoal != null ? !owngoal.equals(goal.owngoal) : goal.owngoal != null) return false;
        if (createdAt != null ? !createdAt.equals(goal.createdAt) : goal.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(goal.updatedAt) : goal.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (personId != null ? personId.hashCode() : 0);
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (minute != null ? minute.hashCode() : 0);
        result = 31 * result + (offset != null ? offset.hashCode() : 0);
        result = 31 * result + (score1 != null ? score1.hashCode() : 0);
        result = 31 * result + (score2 != null ? score2.hashCode() : 0);
        result = 31 * result + (penalty != null ? penalty.hashCode() : 0);
        result = 31 * result + (owngoal != null ? owngoal.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
