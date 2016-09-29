package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "event_standing_entries")
public class EventStandingEntry {
    private Long id;
    private Long eventStandingId;
    private Long teamId;
    private Long pos;
    private Long played;
    private Long won;
    private Long lost;
    private Long drawn;
    private Long goalsFor;
    private Long goalsAgainst;
    private Long pts;
    private String comments;
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
    @Column(name = "event_standing_id", nullable = false)
    public Long getEventStandingId() {
        return eventStandingId;
    }

    public void setEventStandingId(Long eventStandingId) {
        this.eventStandingId = eventStandingId;
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
    @Column(name = "pos", nullable = true)
    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }

    @Basic
    @Column(name = "played", nullable = true)
    public Long getPlayed() {
        return played;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }

    @Basic
    @Column(name = "won", nullable = true)
    public Long getWon() {
        return won;
    }

    public void setWon(Long won) {
        this.won = won;
    }

    @Basic
    @Column(name = "lost", nullable = true)
    public Long getLost() {
        return lost;
    }

    public void setLost(Long lost) {
        this.lost = lost;
    }

    @Basic
    @Column(name = "drawn", nullable = true)
    public Long getDrawn() {
        return drawn;
    }

    public void setDrawn(Long drawn) {
        this.drawn = drawn;
    }

    @Basic
    @Column(name = "goals_for", nullable = true)
    public Long getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Long goalsFor) {
        this.goalsFor = goalsFor;
    }

    @Basic
    @Column(name = "goals_against", nullable = true)
    public Long getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Long goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Basic
    @Column(name = "pts", nullable = true)
    public Long getPts() {
        return pts;
    }

    public void setPts(Long pts) {
        this.pts = pts;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

        EventStandingEntry that = (EventStandingEntry) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (eventStandingId != null ? !eventStandingId.equals(that.eventStandingId) : that.eventStandingId != null)
            return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (pos != null ? !pos.equals(that.pos) : that.pos != null) return false;
        if (played != null ? !played.equals(that.played) : that.played != null) return false;
        if (won != null ? !won.equals(that.won) : that.won != null) return false;
        if (lost != null ? !lost.equals(that.lost) : that.lost != null) return false;
        if (drawn != null ? !drawn.equals(that.drawn) : that.drawn != null) return false;
        if (goalsFor != null ? !goalsFor.equals(that.goalsFor) : that.goalsFor != null) return false;
        if (goalsAgainst != null ? !goalsAgainst.equals(that.goalsAgainst) : that.goalsAgainst != null) return false;
        if (pts != null ? !pts.equals(that.pts) : that.pts != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventStandingId != null ? eventStandingId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (played != null ? played.hashCode() : 0);
        result = 31 * result + (won != null ? won.hashCode() : 0);
        result = 31 * result + (lost != null ? lost.hashCode() : 0);
        result = 31 * result + (drawn != null ? drawn.hashCode() : 0);
        result = 31 * result + (goalsFor != null ? goalsFor.hashCode() : 0);
        result = 31 * result + (goalsAgainst != null ? goalsAgainst.hashCode() : 0);
        result = 31 * result + (pts != null ? pts.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
